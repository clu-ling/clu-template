# {{ cookiecutter.repo_name }}

## What is it?

The `{{ cookiecutter.repo_name }}` is a machine reading system which utilizes [Odin](https://github.com/clu-ling/odin-tutorial) and [statistical models](http://clulab.github.io/processors/metal.html) for parsing, tagging, and rule-based entity/event extraction. The system contains three [sbt subprojects](https://www.scala-sbt.org/1.x/docs/Multi-Project.html), `reader`, and `rest`. The API documentation for each subproject can be found here: [reader](./api/reader/index.html), [rest](./api/rest/index.html).

The `reader` subproject contains the `{{ cookiecutter.repo_name }}`.

The `rest` subproject defines the REST API for the reader.

## How do I use it?

The `{{ cookiecutter.repo_name }}` can be used through a REST API defined in the `rest` subproject. The REST API can be run following the instructions in [Usage](./tutorial.md) after installing.

## Developing

For instructions on developing the `{{ cookiecutter.repo_name }}`, navigate to the [Development](./dev/install.md) section.