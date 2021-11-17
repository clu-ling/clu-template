package {{ cookiecutter.class }}.mr.{{ cookiecutter.project_name }}.ner

import org.clulab.processors.clu.SentencePostProcessor
import org.clulab.processors.Sentence

import com.typesafe.config.{ Config, ConfigFactory }

class CustomNerPostProcessor(config: Config = ConfigFactory.load("reference")) extends SentencePostProcessor {
  
  val stopWords: Set[String] = Set.empty[String]

  override def process(sent: Sentence): Unit = {
    //val seq = sent.entities.get
  }

}