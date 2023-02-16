# MailSender

### Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Run Application](#run)
* [Swagger Documentation](#swagger)

## General info
### The microservice is responsible for sending e-mails. It was created for the purposes of recruitment to Onwelo.

## Technologies
Project is created with:
* Java: 17
* Spring-Boot: 3.0.2
* Gradle: 7.6
* Docker: 4.15.0
* Lombok
* Hibernate
* MySQL

## Setup
Download and run MySQL databases, in the docker directory run:

``` docker-compose -f .\docker-compose.yml up ```

## Run

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the net.czaplinski.mailSender.MailSenderApplication from your IDE.

Alternatively you can use the Spring Boot Gradle plugin like so:
```./gradlew bootRun```

## Swagger

Project uses OpenAPI to provide documentation of endpoints. ```http://localhost:8080/swagger-ui/index.html```