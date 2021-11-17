package {{ cookiecutter.class }}.mr

import org.clulab.odin.Mention

/** Mentions associated with some document metadata */
case class ExtractionResult(
  metadata: DocumentMetadata,
  mention: Mention
)
