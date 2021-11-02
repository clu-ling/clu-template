# clu-template

`clu-template` is a repository template for clu projects. To generate a new clu project from this template, use the following line in the desired directory for the new project.

## Docker

```bash
docker pull parsertongue/clu-template:latest

docker run -i -v $PWD:/output parsertongue/clu-template:latest cookiecutter -o /output /app
```

## Cookiecutter

```bash
cookiecutter https://github.com/clu-ling/clu-template.git
```

<!-- All packages initialized with 0.1 versioning, info.py will need to be updated for description, authors, contact, and license.

Initialized with `example-script`, a dummy script which can be removed.

requirements.txt is rendered with dependencies typical to clu projects, this file will need to be updated for the specific project dependencies.

In setup.py, update keywords and scripts. -->
