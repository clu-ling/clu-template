from typing import List

class AppInfo:
    """
    General information about the application.
    """

    version: str = "0.1"
    description: str = "Package Description"
    authors: List[str] = ["myedibleenso", "zwellington"]
    contact: str = "gus@parsertongue.org"
    repo: str = "https://github.com/clu-ling/{{cookiecutter.repo_name}}"
    license: str = "License, FIXME"

    @property
    def download_url(self) -> str:
        return f"{self.repo}/archive/v{self.version}.zip"


info = AppInfo()