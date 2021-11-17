package {{ cookiecutter.class }}.mr.{{ cookiecutter.project_name }}.odin

import org.clulab.odin._
import {{ cookiecutter.class }}.mr.MentionFilter
import {{ cookiecutter.class }}.mr.actions.OdinActions
import {{ cookiecutter.class }}.mr.{{ cookiecutter.project_name }}.odin._

class CustomActions extends OdinActions {

  def mergeLabels(oldLabels: Seq[String], newLabels: Seq[String]): Seq[String] = {
    (newLabels ++ oldLabels).distinct
  }

  def promoteArgTo(m: Mention, role: String, hyponym: String): Mention = {
    val desiredLabels = taxonomy.hypernymsFor(hyponym)

    val newArgs: Map[String, Seq[Mention]] = m.arguments match {
      case hasArg if m.arguments.contains(role) =>
        val newArgs: Seq[Mention] = m.arguments(role).map { 
          case tb: TextBoundMention     => tb.copy(labels = mergeLabels(tb.labels, desiredLabels))
          case em: EventMention         => em.copy(labels = mergeLabels(em.labels, desiredLabels))
          case rel: RelationMention     => rel.copy(labels = mergeLabels(rel.labels, desiredLabels))
          case cm: CrossSentenceMention => cm.copy(labels = mergeLabels(cm.labels, desiredLabels))
        }
        Map(role -> newArgs)
      case _ => Map.empty[String, Seq[Mention]]
    }

    m match {
      case tb: TextBoundMention     => tb
      case em: EventMention         => em.copy(arguments = em.arguments ++ newArgs)
      case rel: RelationMention     => rel.copy(arguments = rel.arguments ++ newArgs)
      case cm: CrossSentenceMention => cm.copy(arguments = cm.arguments ++ newArgs)
    }
  }

  def distinctArgs(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = mentions map { mention => 
    val newArgs: Map[String, Seq[Mention]] = mention.arguments.keys.map{ arg => 
      arg -> mention.arguments(arg).distinct
    }.toMap

    mention match {
        // invoke copy constructor for Mention subtypes w/ args
        case em: EventMention         => em.copy(arguments = newArgs)
        case rel: RelationMention     => rel.copy(arguments = newArgs)
        case cm: CrossSentenceMention => cm.copy(arguments = newArgs)
        case m                        => m
    }
  }

  def cleanupEntities(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    val validEntities  = MentionFilter.validEntities(mentions)
    val res1 = keepLongestByLabel(validEntities, "TimeExpression")
    // now cleanup concepts and keep longest (the nested concept containing all simpler cases)
    val res2 = res1.filter(_.foundBy == "concept-coord-mod")
    // Discard "concept-coord-mod" mentions with core notfoundBy "base-concept"
    val toKeep = res2.filter(_.arguments("core").forall(_.foundBy == "base-concept"))
    // Keep only the longest mentions foundBy concept-serial-mod
    val res3 = res1.filterNot(_.foundBy == "concept-coord-mod")
    val prunedEntities = keepLongestByLabel(res3, "Concept")
    val entities = MentionFilter.keepLongestMentions(prunedEntities)
    (entities ++ toKeep).distinct
  }

  def finalSweep(mentions: Seq[Mention], state: State = new State()): Seq[Mention] = {
    val labelsToDiscard = Seq("VerbPhrase", "Modifier")
    //mentions.foreach{ m => println(s"MENTION: text:\t${m.text}\t(${m.label})\t${m.foundBy}") }
    val shortEnough = MentionFilter.keepShortSpans(mentions)
    val longest = MentionFilter.keepLongestMentions(shortEnough)
    //Discard VerbPhrase and Modifier mentions
    val filtered = longest.filterNot(s => labelsToDiscard.exists(m => {s matches m}))
    //val events = MentionFilter.disallowOverlappingArgs(filtered)
    val remaining = filtered

    def relabelQuery(orig: String, need: Mention): String = need match {
      case other                             => orig
    }

    // relabel queries
    remaining.map {
      case q if q matches "Query" => 
        q match {
          case em: EventMention =>
            val need = em.arguments("need").head 
            val label = relabelQuery(em.label, need)
            em.copy(labels = (Seq(label) ++ em.labels).distinct)
          case rel: RelationMention =>
            val need = rel.arguments("need").head 
            val label = relabelQuery(rel.label, need)
            rel.copy(labels = (Seq(label) ++ rel.labels).distinct)
          case other => other
        }
      case other => other
    }
  }

  def keepLongestByLabel(mentions: Seq[Mention], label: String, state: State = new State()): Seq[Mention] = {
    val res = mentions.partition(_ matches label)
    val toFilter = res._1
    //val toFilter = (res._1 ++ state.allMentions.filter(_ matches label)).distinct
    val other = res._2
    val mns: Iterable[Mention] = for {
    // find mentions in same sentence
      (k, v) <- toFilter.groupBy(_.sentence)
      m <- v
      // for overlapping mentions, keep only the longest
      numOverlapping = v.filter(_.tokenInterval.toSet.intersect(m.tokenInterval.toSet).nonEmpty)
      longest = numOverlapping.maxBy(_.tokenInterval.length)
    } yield longest
    mns.toVector.distinct ++ other
  }
}