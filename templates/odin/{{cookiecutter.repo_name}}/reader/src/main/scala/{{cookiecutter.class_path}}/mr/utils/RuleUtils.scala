package {{ cookiecutter.class }}.mr.utils

import org.clulab.openie.ResourceUtils
import org.clulab.odin.impl.Taxonomy

import scala.util.matching.Regex

import java.util.Collection

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor


object RuleUtils {

  private val URL_PATTERN = new Regex("""(?i)^(http|ftp|localhost|file).+""")

  def read(rulesPath: String): String = rulesPath match {

    case url if URL_PATTERN.pattern.matcher(url).matches =>
      scala.io.Source.fromURL(url).mkString

    case resource =>
      val stream = getClass.getClassLoader.getResourceAsStream(rulesPath)
      val source = scala.io.Source.fromInputStream(stream)
      val data = source.mkString
      source.close()
      data

  }

  def readTaxonomy(path: String): Taxonomy = {
    val input = read(path)
    val yaml = new Yaml(new Constructor(classOf[Collection[Any]]))
    val data = yaml.load(input).asInstanceOf[Collection[Any]]
    Taxonomy(data)
  }
}