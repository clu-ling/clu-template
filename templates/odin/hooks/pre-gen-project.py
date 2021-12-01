import sys
import requests

{% set class_dir = cookiecutter.class_path.replace('.','/') %}

repo_org = "{{ cookiecutter.repo_organization }}"
git_link = f"http://github.com/{repo_org}"
response = requests.get(git_link)
if not response.status_code == 200:
    print('ERROR: %s is not a valid repository organization!' % repo_org)