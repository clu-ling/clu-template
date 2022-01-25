package {{ cookiecutter.class_path }}.mr

import org.clulab.odin._
import org.clulab.processors.{ Document => CluDocument }

/** Extractor that avoids certain mentions */
class AvoidAwareExtractor(
  rules: String,
  actions: Actions = new Actions,
  globalAction: Action = identityAction,
  avoidRules: String,
  avoidActions: Actions = new Actions,
  avoidGlobalAction: Action = identityAction
) extends OdinExtractor(
  rules        = rules,
  actions      = actions,
  globalAction = globalAction
) {

  val avoidEngine  = ExtractorEngine(rules = avoidRules, actions = avoidActions, globalAction = avoidGlobalAction)

  override def extract(doc: CluDocument, state: State): Seq[Mention] = {
    val avoidState: State = State(avoidEngine.extractFrom(doc, state))
    baseEngine.extractFrom(doc, avoidState)
  }
}