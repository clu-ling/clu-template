# Installation

{% if cookiecutter.include_docker_ci == 'y' %}
`{{cookiecutter.repo_name}}` can be run one of two ways:

1. Local installation of [Python library (>= v3.8)](https://conda.io/projects/conda/en/latest/user-guide/install/index.html)
2. Using [Docker](https://docs.docker.com/get-docker/)
{% else %}
`{{cookiecutter.repo_name}}` can be run using a local installation of the [Python library (>= v3.8)](https://conda.io/projects/conda/en/latest/user-guide/install/index.html)
{% endif %}
## Python

### Requirements
- [Python (>= v3.8)](https://conda.io/projects/conda/en/latest/user-guide/install/index.html)

### Install
To install directly from the default branch of the repository:

```bash
pip install git+https://github.com/{{cookiecutter.repo_organization}}/{{cookiecutter.repo_name}}.git
```


```bash
# install the pre-commit hooks (as a convenience)
pre-commit install -t pre-push
```

{% if cookiecutter.include_docker_ci == 'y' %}
## Docker

### Requirements

- [Docker](https://docs.docker.com/get-docker/)

### Install

Docker images are periodically published to [DockerHub](https://hub.docker.com/r/{{cookiecutter.image_organization}}/{{cookiecutter.image_name}}):

```bash
docker pull "{{cookiecutter.image_organization}}/{{cookiecutter.image_name}}:latest"
```
{% endif %}