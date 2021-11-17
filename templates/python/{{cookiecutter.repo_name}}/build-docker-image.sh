DOCKERFILE="Dockerfile"
ORG="{{cookiecutter.image_organization}}"
IMAGE_NAME="{{cookiecutter.image_name}}"
docker buildx build --output=type=docker --platform linux/amd64 -f ${DOCKERFILE} -t "${ORG}/${IMAGE_NAME}:latest" -t "${ORG}/${IMAGE_NAME}:amd64" .
