# Micronaut 4.9.1 Documentation - app (pipe)

- [User Guide](https://docs.micronaut.io/4.9.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.9.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.9.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

- [Micronaut Maven Plugin documentation](https://micronaut-projects.github.io/micronaut-maven-plugin/latest/)

## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)

## Feature maven-enforcer-plugin documentation

- [https://maven.apache.org/enforcer/maven-enforcer-plugin/](https://maven.apache.org/enforcer/maven-enforcer-plugin/)

## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)

## Docker no projeto

Saiba mais sobre como usar o docker no projeto

### Dockerimage lint (on Local Ubuntu Linux)

```bash
    wget -O hadolint https://github.com/hadolint/hadolint/releases/download/v2.12.0/hadolint-Linux-x86_64

    sudo mv hadolint /usr/local/bin/hadolint

    sudo chmod +x /usr/local/bin/hadolint

    hadolint --version

    hadolint Dockerfile

```

### Docker build

- Build the Docker image

`docker build -t kubernetes-labs-app-micronout:latest .`

- Run the container using

```bash
docker run -d \
  -p 8080:8080 \
  -e MOCKAPI_PROJECT_SECRET=your_mockapi_secret \
  -e MOCKAPI_PATH_PREFIX=mockapi.io \
  -e MOCKAPI_VERSION=api/v1 \
  --name app-micronout \
  kubernetes-labs-app-micronout:latest
```

- Verify the container is running
`docker ps`

- Check the application logs
`docker logs app-micronout`

- Test the application health endpoint:
`curl http://localhost:8080/health`

- To stop and remove the container:

```bash
docker stop app-micronout
docker rm app-micronout
```


## Conventional Commits

- [conventionalcommits](https://www.conventionalcommits.org/en/v1.0.0/)
- [commitlint](https://commitlint.js.org/)
- [typicode](https://typicode.github.io/husky/)

## Maven

Execute no terminal

`mvn wrapper:wrapper`


## Git tag name

`git tag <name>`

## Git push tag

`git push origin v2.0.1`

## Micrometer

[Doc. micronaut-micrometer](https://micronaut-projects.github.io/micronaut-micrometer/latest/guide/#metricsConcepts)
