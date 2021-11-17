import os
import shutil

def remove(filepath):
    if os.path.isfile(filepath):
        os.remove(filepath)
    elif os.path.isdir(filepath):
        shutil.rmtree(filepath)

include_docker = '{{cookiecutter.include_docker_ci}}' == "y"
include_rest = '{{cookiecutter.include_rest_api}}' == "y"

if not include_docker:
    remove("Dockerfile")
    remove(".dockerignore")
    remove("build-docker-image.sh")
    remove(os.path.join(".github", "workflows", "docker.yml"))

if not include_rest:
    # remove absolute path to file nested inside the generated folder
    # os.getcwd(), "{{cookiecutter.repo_name}}", 
    remove(os.path.join("bin", "{{cookiecutter.repo_name}}-rest-api"))

print("Finished generating {{cookiecutter.repo_name}}")