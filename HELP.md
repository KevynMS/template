## Directories structure

| Directory/file                | Description                                                               |
|-------------------------------|---------------------------------------------------------------------------|
| README.md                     | Short service description and instructions how to build, run and deploy.  |      
| build.gradle, settings.gradle | Gradle build file                                                         |   
| gradle, gradlew, gradlew.bat  | Gradle wrapper                                                            |          
| .github                       | GitHub workflow                                                           |
| Dockerfile, .dockerignore     | Docker building intructions                                               |
| *servicename*-openapi.yaml    | OpenAPI specification.                                                    |
| README.md                     | Instructions for developers                                               | 
| src/main/avro                 | Avro files                                                                |
| src/main/java                 | Java classes                                                              |
| src/main/resources            | Application properties and other resources                                |
| src/test/java                 | Java unit tests                                                           |
| src/test/resources            | Test profile properties and other test resources                          |

## Source code packages structure

| Package                                   | Description                                                                           |
|-------------------------------------------|---------------------------------------------------------------------------------------|
| com.spoxx.*servicename*                   | Root package for all service classes                                                  | 
| com.spoxx.*servicename*.configuration     | Spring configuration                                                                  |
| com.spoxx.*servicename*.controller        | Rest controllers                                                                      |
| com.spoxx.*servicename*.controller.common | Rest utility classes without service specific logic                                   |
| com.spoxx.*servicename*.controller.model  | Data transfer objects for Rest API                                                    | 
| com.spoxx.*servicename*.kafka             | Kafka consumers and producers                                                         | 
| com.spoxx.*servicename*.kafka.common      | Kafka utility classes without service specific logic                                  | 
| com.spoxx.*servicename*.kafka.mockload    | Mock implementation of consumers and producers for testing in development environment | 
| com.spoxx.*servicename*.mongo.document    | MongoDB Documents definition                                                          |
| com.spoxx.*servicename*.mongo.repository  | MongoDB data access classes                                                           |
| com.spoxx.*servicename*.service           | Business logic layer which connects all other layers together                         |