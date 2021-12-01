package {{ cookiecutter.class_path }}.mr.{{ cookiecutter.project_name }}.processors

import {{ cookiecutter.class_path }}.mr.{{ cookiecutter.project_name }}.ner.CustomKbLoader
import com.typesafe.config.{ Config, ConfigFactory }
import com.typesafe.scalalogging.LazyLogging
import org.clulab.dynet
import org.clulab.processors.Document
import org.clulab.processors.clu.{ CluProcessor, SentencePostProcessor }
import org.clulab.processors.fastnlp.FastNLPProcessor
import org.clulab.processors.shallownlp.ShallowNLPProcessor
//import org.clulab.processors.clu.sequences.EnglishPOSPostProcessor
import org.clulab.sequences.Tagger
import org.clulab.struct.{ DirectedGraph, Edge, GraphMap }
import org.clulab.utils.Configured
import {{ cookiecutter.class_path }}.mr.{{ cookiecutter.project_name }}.ner.CustomNerPostProcessor

import scala.util.{ Failure, Success, Try }


/**
  * Custom [[org.clulab.processors.Processor]] for {{ cookiecutter.project_name }}.
  */
class CustomProcessor(
  val config: Config = ConfigFactory.load("customprocessor")
) extends FastNLPProcessor(
  tokenizerPostProcessor = None,
  internStrings = false,
  withChunks = false,
  withRelationExtraction = false,
  withDiscourse = ShallowNLPProcessor.NO_DISCOURSE
) with LazyLogging 
  with Configured {
  val prefix = "processor"

  dynet.Utils.initializeDyNet(autoBatch = false, mem = "2048,2048,2048,2048")

  override def getConf: Config = config

  val PREDICATE_ATTACHMENT_NAME = "predicates"
  
  // one of the multi-task learning (MTL) models, which covers: NER
  lazy val mtlNer: dynet.Metal = { 
    dynet.Metal(getArgString(s"$prefix.ner.mtl-ner", None))
  }

  // one of the multi-task learning (MTL) models, which covers: POS, chunking, and SRL (predicates)
  lazy val mtlPosChunkSrlp: dynet.Metal = { 
    dynet.Metal(getArgString(s"$prefix.mtl-pos-chunk-srlp", None))
  }

  // one of the multi-task learning (MTL) models, which covers: SRL (arguments)
  lazy val mtlSrla: dynet.Metal = { 
    dynet.Metal(getArgString(s"$prefix.mtl-srla", None))
  }

  /** NER; modifies the document in place */
  override def recognizeNamedEntities(doc: Document) {
    basicSanityCheck(doc)
    for(sent <- doc.sentences) {
      val allLabels = mtlNer.predictJointly(new dynet.AnnotatedSentence(sent.words))
      sent.entities = Some(allLabels(0).toArray)
    }
  }

  override def srl(doc: Document): Unit = {

    // generate SRL frames for each predicate in each sentence
    for (sentence <- doc.sentences) {

      val (tags, predicates): (Array[String], Array[Int]) = {
        // FIXME: find out about taskId, and switch to predict()
        val allLabels = mtlPosChunkSrlp.predictJointly(new dynet.AnnotatedSentence(sentence.words))
        val posTags = allLabels(0).toArray
        val preds: Array[Int] = allLabels(2).filter{ case lbl => lbl == "B-P" }.indices.map(_ + 1).toArray

        (posTags, preds)
      }
      //val tags = sentence.tags.get

      // SRL needs POS tags and NEs!
      val annotatedSentence =
        new dynet.AnnotatedSentence(sentence.words,
          Some(tags), Some(sentence.entities.get))

      // all predicates become roots
      val roots = predicates

      val argLabels = for (pred <- predicates) {
        mtlSrla.predict(0, annotatedSentence, Some(pred))
      }

      val edges: Seq[Edge[String]] = for {
        pred <- predicates
        argLabels = mtlSrla.predict(0, annotatedSentence, Some(pred))
        ai <- argLabels.indices
        argLabel = argLabels(ai)
        if argLabel != "O"
      } yield Edge[String](pred, ai, argLabel)

      val srlGraph = new DirectedGraph[String](edges.toList, roots.toSet)
      sentence.graphs += GraphMap.SEMANTIC_ROLES -> srlGraph
    }
  }
}
