package {{ cookiecutter.class_path }}.mr.events

import {{ cookiecutter.class_path }}.mr.OdinExtractor
import org.clulab.odin._
import org.clulab.processors.Document

class OdinEventFinder(
  rules: String,
  actions: Actions = new Actions,
  globalAction: Action = identityAction,
  finalAction: Action  = identityAction
) extends OdinExtractor(
  rules = rules,
  actions = actions,
  globalAction = globalAction,
  finalAction = finalAction
) with EventFinder { }