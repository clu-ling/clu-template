import re
import sys
import requests

# single author: 'zwellington'
# multi-author: ['myedibleenso', 'zwellington']
AUTHOR_REGEX = r"\[?('\w+',? ?)+\]?"

authors = "{{ cookiecutter.authors }}"

if not re.match(AUTHOR_REGEX, authors):
    print('ERROR: %s is not a valid author(list)!' % authors)
    sys.exit(1)

repo_org = "{{ cookiecutter.repo_organization }}"
git_link = f"http://github.com/{repo_org}"
response = requests.get(git_link)
if not response.status_code == 200:
    print('ERROR: %s is not a valid repository organization!' % repo_org)