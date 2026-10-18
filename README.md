# Spring Boot and RabbitMQ

Exploring RabbitMQ basics in Spring Boot. This repository demonstrates how to route and process messages using custom exchanges and multiple queues. It includes a publisher and a consumer that handle `premium` and `standard` orders.

## Features

* **Dockerized RabbitMQ:** Local message broker setup with the management UI enabled.
* **Order Publisher (Spring Boot):** REST API that publishes order payloads to RabbitMQ exchanges.
* **Order Consumer (Spring Boot):** Service listening to multiple queues to process incoming `premium` and `standard` orders.

## Prerequisites

* Java 25+
* Maven
* Docker

## Getting Started

### 1. Start RabbitMQ

Run the RabbitMQ broker locally in a Docker container:

```bash
docker run -d --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
```

* **Management UI:** [http://127.0.0.1:15672/](http://127.0.0.1:15672/)
* **Username:** `guest` (lowercase)
* **Password:** `guest` (lowercase)

### 2. Run the Publisher

Start the Spring Boot *Publisher* application. Once it is up and running on port `8080`, send a few test orders using `curl`:

Send a *Premium* Order:

```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"product": "Product 1", "orderType": "premium"}'
```

Send a *Standard* Order:

```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"product": "Product 2", "orderType": "standard"}'
```

Log in to the RabbitMQ Management UI at `http://localhost:15672` and check the *Queues* tab to see the queued messages waiting to be processed.

### 3. Run the Consumer

Start the Spring Boot *Consumer* application. It listens to both the `premium` and `standard` queues and will consume and log all pending messages from RabbitMQ.
