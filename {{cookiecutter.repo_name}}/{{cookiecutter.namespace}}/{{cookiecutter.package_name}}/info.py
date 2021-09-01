from typing import List

class AppInfo:
    """
    General information about the application.

    ***This repo was generated from a cookiecutter template published by myedibleenso and zwellington.
    See https://github.com/clu-ling/clu-template for more info.
    """

    version: str = "0.1"
    description: str = "{{cookiecutter.description}}"
    authors: List[str] = {{cookiecutter.authors}}
    contact: str = "{{cookiecutter.contact_info}}"
    repo: str = "https://github.com/{{cookiecutter.repo_organization}}/{{cookiecutter.repo_name}}"
    license: str = "{{cookiecutter.license}}"

    @property
    def download_url(self) -> str:
        return f"{self.repo}/archive/v{self.version}.zip"


info = AppInfo()