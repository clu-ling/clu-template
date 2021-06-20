DOCKERFILE="Dockerfile"
ORG="parsertongue"
IMAGE_NAME="{{cookiecutter.package_image_name}}"
docker buildx build --output=type=docker --platform linux/amd64 -f ${DOCKERFILE} -t "${ORG}/${IMAGE_NAME}:latest" -t "${ORG}/${IMAGE_NAME}:amd64" .