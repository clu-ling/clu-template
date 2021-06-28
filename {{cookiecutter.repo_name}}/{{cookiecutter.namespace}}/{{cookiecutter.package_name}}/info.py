from typing import List

class AppInfo:
    """
    General information about the application.

    ***This repo was generated from a cookiecutter template published by myedibleenso and zwellington.
    See https://github.com/clu-ling/clu-template for more info.
    """

    version: str = "0.1"
    description: str = "FIXME: Package Description"
    authors: List[str] = "{{cookiecutter.authors}}"
    contact: str = "FIXME: Contact info"
    repo: str = "https://github.com/clu-ling/{{cookiecutter.repo_name}}"
    license: str = "FIXME: License"

    @property
    def download_url(self) -> str:
        return f"{self.repo}/archive/v{self.version}.zip"


info = AppInfo()