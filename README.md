#Description

OpenPayd Foreign Exchange Application

#Getting Started

mvn package

docker build -t casestudy .

docker run -p 8088:8088 casestudy

#Tech Stack

Java 11 / Spring-Boot / Maven / H2 Database / JUnit / Swagger / Docker

#Swagger

http://localhost:8088/api/swagger-ui.html

#API Documentation

http://localhost:8088/api/v2/api-docs