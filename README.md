# GitActionsLearner

A Spring Boot application used to practise GitHub Actions workflows and Docker image publishing.

## Stack

- Java 17 / Spring Boot 3.2.1
- Maven (wrapper included)
- Docker / Docker Hub

## Project structure

```
.github/workflows/github-actions.yml   # CI/CD pipeline
src/main/java/.../controller/          # REST endpoints
src/main/java/build/dockerfile/        # Dockerfile
src/main/resources/application-local.yml
pom.xml
```

## Running locally

```bash
./mvnw clean install
java -jar target/github-actions-playground.jar --spring.profiles.active=local
```

The app starts on port **9001** and exposes a single endpoint:

| Method | Path | Response |
|--------|------|----------|
| GET    | `/`  | `Exploring git actions` |

## GitHub Actions workflow

The workflow (`.github/workflows/github-actions.yml`) triggers on:

- **push** to `main`
- **pull request** targeting any branch

### Steps

1. Checkout source
2. Set up JDK 17 (Temurin, Maven cache)
3. Build JAR — `mvn clean install -DskipTests -Dprofile=containerizeapp`
4. Log in to Docker Hub using `DOCKER_USERNAME` / `DOCKER_PASSWORD` secrets
5. Build and push the Docker image — push only happens when the base ref is `main`

### Required secrets

| Secret | Purpose |
|--------|---------|
| `DOCKERHUB_USERNAME` | Docker Hub username (used to name the image) |
| `DOCKER_USERNAME` | Docker Hub login username |
| `DOCKER_PASSWORD` | Docker Hub login password |

## Docker image

The Dockerfile copies the built JAR into a Tomcat base image and starts it with the `local` Spring profile on port 9001.

```bash
docker pull <DOCKERHUB_USERNAME>/github-actions-playground:latest
docker run -p 9001:9001 <DOCKERHUB_USERNAME>/github-actions-playground:latest
```
