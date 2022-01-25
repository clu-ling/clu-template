from typing import List, Text

class AppInfo:
    """
    General information about the application.

    ***This repo was generated from a cookiecutter template published by myedibleenso and zwellington.
    See https://github.com/clu-ling/clu-template for more info.
    """

    version: Text = "0.1"
    description: Text = "{{cookiecutter.description}}"
    {% set authors_list = cookiecutter.authors.split(',') %}
    authors: List[Text] = {{ authors_list }}
    contact: Text = "{{cookiecutter.contact_info}}"
    repo: Text = "https://github.com/{{cookiecutter.repo_organization}}/{{cookiecutter.repo_name}}"
    license: Text = "{{cookiecutter.license}}"

    @property
    def download_url(self) -> str:
        return f"{self.repo}/archive/v{self.version}.zip"


info = AppInfo()