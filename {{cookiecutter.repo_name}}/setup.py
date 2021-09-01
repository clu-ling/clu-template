from setuptools.command.install import install
from setuptools.command.develop import develop
from setuptools import setup

from clu.{{cookiecutter.package_name}}.info import info

# class PackageDevelop(develop):
#     def run(self):
#         develop.run(self)


# class PackageInstall(install):
#     def run(self):
#         # install everything else
#         install.run(self)


# use requirements.txt as deps list
with open("requirements.txt", "r") as f:
    required = f.read().splitlines()

# get readme
with open("README.md", "r") as f:
    readme = f.read()

test_deps = required + ["green>=2.5.0", "coverage", "mypy"]
# NOTE: <packagename> @ allows installation of git-based URLs
dev_deps = test_deps + [
    "black @ git+git://github.com/psf/black.git",
    "mkdocs==1.2.1",
    # "portray @ git+git://github.com/myedibleenso/portray.git@issue/83",
    # "portray @ git+git://github.com/myedibleenso/portray.git@avoid-regressions",
    # "mkapi==1.0.14",
    "pdoc3==0.9.2",
    "mkdocs-git-snippet==0.1.1",
    "mkdocs-git-revision-date-localized-plugin==0.9.2",
    "mkdocs-git-authors-plugin==0.3.3",
    "mkdocs-rtd-dropdown==1.0.2",
    "pre-commit==2.13.0",
]

setup(
    name="{{cookiecutter.repo_name}}",
    packages=["clu.{{cookiecutter.package_name}}"],
    version=info.version,
    keywords=["nlp"],
    description=info.description,
    long_description=readme,
    url=info.repo,
    download_url=info.download_url,
    author=" and ".join(info.authors),
    author_email=info.contact,
    license=info.license,
    # see https://python-packaging.readthedocs.io/en/latest/command-line-scripts.html
    scripts=[
        "bin/example-script",
        "bin/{{cookiecutter.repo_name}}-rest-api"
        ],
    install_requires=required,
    classifiers=[
        "Intended Audience :: Science/Research",
        "Natural Language :: English",
        "Topic :: Scientific/Engineering :: Artificial Intelligence",
        "Programming Language :: Python :: 3",
    ],
    tests_require=test_deps,
    extras_require={
        "test": test_deps,
        "dev": dev_deps,
        "all": test_deps
        # 'docs': docs_deps
    },
    include_package_data=True,
    zip_safe=False,
)