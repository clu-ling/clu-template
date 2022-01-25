FROM python:3.8

LABEL author="Zack Wellington"
LABEL description="cookiecutter base image for repo generation"

WORKDIR /app

COPY . .

RUN pip install -U \
    pip \
    cookiecutter==1.7.3

CMD ["cookiecutter", "--no-input", "/app", "--directory='templates/python'"]