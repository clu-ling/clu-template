[![Project CI](https://github.com/{{ cookiecutter.repo_organization }}/{{ cookiecutter.repo_name }}/actions/workflows/ci.yml/badge.svg)](https://github.com/{{ cookiecutter.repo_organization }}/{{ cookiecutter.repo_name }}/actions/workflows/ci.yml) [![Code style: black](https://img.shields.io/badge/code%20style-black-000000.svg)](https://github.com/psf/black)

# {{cookiecutter.repo_name}}

{{ cookiecutter.description }}

For details, [see the docs](https://{{ cookiecutter.repo_organization }}.github.io/{{ cookiecutter.repo_name }}).

## Build

```docker build -f Dockerfile -t {{ cookiecutter.image_organization }}/{{ cookiecutter.image_name }}:latest .```
