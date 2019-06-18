# Vehicle Tracking with Spring Boot Reactive


This example application will consume vehicle positional GPS data from RabbitMQ, save the data into MongoDB and process the messages and publish via web sockets and HTTP streams. 

* Spring Data Reactive MongoDB
* Reactive WebSocket
* HTTP Stream (Using a FLUX)

## Prerequisites

* [RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [MongoDB](https://www.mongodb.com/download-center/community)
* [GIT](https://git-scm.com/downloads)
* [Docker](https://www.docker.com/get-started)

###Docker Containers 

`$ docker run -d --name rabbitmq -p 5672:5672 rabbitmq:3`

`$ docker run -d -p 27017-27019:27017-27019 --name mongodb mongo:4.0.4`