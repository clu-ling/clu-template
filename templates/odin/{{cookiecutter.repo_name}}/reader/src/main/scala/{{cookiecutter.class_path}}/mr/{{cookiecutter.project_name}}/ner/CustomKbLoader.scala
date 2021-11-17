package {{ cookiecutter.class }}.mr.{{ cookiecutter.project_name }}.ner

import ai.lum.common.ConfigUtils._
import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging
import org.clulab.sequences.{ LexiconNER, NoLexicalVariations }
import org.clulab.struct.TrueEntityValidator
import {{ cookiecutter.class }}.mr.ner.KbLoader
import java.io._
import java.util.MissingResourceException
import java.util.zip.{ GZIPInputStream, GZIPOutputStream }

//import org.clulab.utils.Files


/**
  * [[KbLoader]] for {{ cookiecutter.project_name }}.
  */
class CustomKbLoader(val config: Config = ConfigFactory.load("reference"))
  extends KbLoader with LazyLogging {

  /** If a serialized NER model already exists, it will be at this location */
  // FIXME: update conf
  val nerModel: Option[String] = config.get[String]("{{ cookiecutter.class }}.mr.ner.model")
  val kbs: Seq[String] = config[List[String]]("{{ cookiecutter.class }}.mr.ner.kbs")
  val overrides: Option[Seq[String]] =  config.get[List[String]](s"{{ cookiecutter.class }}.mr.ner.overrides")
  val stopListFile: Option[String] = config.get[String]("{{ cookiecutter.class }}.mr.ner.stopListFile")

  override def nerFromKbs(): LexiconNER = LexiconNER(
      kbs,
      overrides,
      new TrueEntityValidator,
      new NoLexicalVariations,
      useLemmasForMatching = false,
      caseInsensitiveMatching = true
  )  
}

object AgKbLoader {
  /**
    * Creates the serialized LexiconNER model from the provided KBs.
    * @param args Any args are ignored. Refer to reference.conf
    */
  def main(args: Array[String]): Unit = {
    
    val loader = new CustomKbLoader()
    // resource path to export the NER model
    val modelPath = loader.config[String]("{{ cookiecutter.class }}.mr.ner.model")

    // the NER model itself
    val ner = loader.nerFromKbs()

    loader.serializeNer(ner, modelPath)
  }
}