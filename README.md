# Flakkee-verhuizers API

API modulo of the Flakkee-verhuizers application

See more details about project structure in [HELP.md](HELP.md).

## Build and run on development environment

### Prerequisites

- Install Java 11

- Setup [GitHub PAT](https://github.com/SPOXX/docs/blob/master/src/microservices/java.md#setup-development-environment)

### Build

Build with Gradle

```shell
./gradlew clean build
```

### Run

Run development infrastructure from [development-environment.git](https://github.com/SPOXX/development-environment)

Run service with Gradle:
```shell
./gradlew bootRun --args='--spring.profiles.active=dev'
```

Profile `dev` setup hosts and ports for development environment. Also, it disables service commands' handler and enables Swagger UI.

Swagger UI: http://localhost:8080/swagger/swagger-ui/index.html

## OpenAPI

Note: this file is not updated automatically. Developer must update it manually after any changes in Rest methods.
The newest version can be downloaded from Swagger UI.  

## Configuration

Memory: 128 MB should be enough.
