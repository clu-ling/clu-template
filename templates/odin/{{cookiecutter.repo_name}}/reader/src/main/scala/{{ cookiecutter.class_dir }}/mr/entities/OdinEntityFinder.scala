package {{ cookiecutter.class_path }}.mr.entities

import {{ cookiecutter.class_path }}.mr.OdinExtractor
import org.clulab.odin.{ Action, Actions, identityAction }


class OdinEntityFinder(
  rules: String,
  actions: Actions = new Actions,
  globalAction: Action = identityAction,
  finalAction: Action = identityAction
) extends OdinExtractor(
  rules = rules,
  actions = actions,
  globalAction = globalAction,
  finalAction = finalAction
) with EntityFinder { }
