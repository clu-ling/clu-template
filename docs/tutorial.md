# Usage

There are two methods to generate a new repository from this template:

## Docker

If you have docker, the following commands can be used to generate a new repository:

```bash
docker pull parsertongue/clu-template:latest
```

Pulls the pre-built image from Dockerhub.

```bash
docker run -i parsertongue/clu-template:latest cookiecutter -o /path/to/output /app
```

Replace `/path/to/output` with the desired location for the local repository. This command will prompt the user for `cookiecutter.json` values, to accept the defaults just press `return`.

**NOTE: The user can define a config.yaml and use the following command to avoid input prompts:

```bash
docker run -i parsertongue/clu-template:latest cookiecutter --config-file /path/to/config.yaml -o /path/to/output /app
```

See [the cookiecutter documentation](https://cookiecutter.readthedocs.io/en/1.7.2/advanced/user_config.html) for more information on this method.

### Alternative

You can also avoid the `-o` flag by first navigating to the desired output directory and running this command:

```bash
docker run -i -v $PWD:/app parsertongue/clu-template:latest cookiecutter https://github.com/clu-ling/clu-template.git
```

The same prompts will occur (and the config option still works).

## Cookiecutter

If you don't have Docker, you can also use the template by installing [cookiecutter](https://cookiecutter.readthedocs.io/en/1.7.2/installation.html) and running the command:

```bash
cookiecutter https://github.com/clu-ling/clu-template.git
```

Again, you can either use the `-o` flag and specify the output or run this command under the desired directory. The config option is still avaiable.

## User Inputs

| Variable | Default |
| :----: | :----: |
| organization | clu-ling |
| namespace | clu |
| package_name | fixme |
| repo_name | clu-fixme |
| package_image_name | clu-fixme |
| authors | [myedibleenso, zwellington] |

**NOTE: By default the `repo_name` and `package_image_name` are set as `namespace-package_name`.
