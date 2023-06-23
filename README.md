# Cinema Application
> This project is a RESTful web application for booking seats in a cinema. It's purpose was to explore Spring Boot 3 with new Spring Security 6 features


## General Information
The main purpose of this application is to allow users to book a seat in a cinema. Its backend has fully functional
endpoints that enable users to register, login, and access the main functionality of booking a seat for a screening.
The administration of rooms, films, and screenings is managed by an admin who has exclusive access to the required endpoints



## Technologies and frameworks
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA & Hibernate
- Spring Security 6
- Gradle
- PostgreSQL
- JWT
- OpenAPI
- JUnit 5 and Mockito


## Reference documentation
-  [JWT](https://jwt.io/)
-  [Spring Security 6](https://docs.spring.io/spring-security/reference/index.html)
-  [OpenAPI](https://swagger.io/specification/)

## Setup
For properly set environmental variables and gradle installed, follow the setup below:
- Clone this repository

`$ git clone https://github.com/kubabukry/cinema-application.git`
- Go into the project directory

`$ cd <project-directory>`
- Run the following command to build application

`$ ./gradlew build`
- Run the following command to start the application

`$ ./gradlew bootRun`


## Usage
Please refer to the API documentation GUI available at runtime:

`http://localhost:<server-port>/swagger-ui/index.html#`

or just in JSON format to load in API hubs

`http://localhost:<server-port>/v3/api-docs`

This endpoint is not protected by security for development purposes only.

Server port is `8080` by default


## Author
Created by Jakub Bukry
