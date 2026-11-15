# kafka
# Kafka Microservices

A hands-on microservices project built with **Java 21, Spring Boot, and Apache Kafka**, demonstrating asynchronous communication between independent services using an Event-Driven Architecture.

## Architecture

The project consists of two independent Spring Boot microservices:

- **Order Service:** Creates orders, publishes order events, and manages order statuses.
- **Payment Service:** Consumes order events, processes payment logic, and publishes payment results.

### Communication Flow

1. Order Service receives a new order request.
2. Order Service publishes an `OrderCreatedEvent` to the `order-created` Kafka topic.
3. Payment Service consumes the event and processes the payment.
4. Payment Service publishes the payment result:
   - `payment-result` — successful payment.
   - `payment-failed` — failed payment.
5. Order Service consumes the result and updates the order status.

## Technologies

- Java 21
- Spring Boot
- Apache Kafka
- Spring Kafka
- Gradle
- REST API
- JSON Serialization / Deserialization

## Key Features

- Event-Driven Microservices Communication
- Kafka Producers and Consumers
- Asynchronous Message Processing
- Consumer Groups
- JSON Message Serialization and Deserialization
- Retry Mechanism
- Error Handling with `DefaultErrorHandler`
- Order Status Management

## Microservices

| Service | Port | Responsibility |
|---|---:|---|
| Order Service | 8080 | Order creation and status management |
| Payment Service | 8086 | Payment processing |

## Kafka Topics

| Topic | Purpose |
|---|---|
| `order-created` | New order events |
| `payment-result` | Successful payment results |
| `payment-failed` | Failed payment events |

## Running the Project

### Prerequisites

- Java 21
- Apache Kafka
- Gradle (or the included Gradle Wrapper)

### 1. Start Kafka

Start your Kafka controller and broker. The services are configured to connect to:

`localhost:9092`

### 2. Start Order Service

Navigate to the `orderservice` directory and run:

```bash
./gradlew bootRun
```

### 3. Start Payment Service

Navigate to the `payment` directory and run:

```bash
./gradlew bootRun
```

On Windows, use `gradlew.bat bootRun`.

## Testing the API

Order Service Swagger UI:

`http://localhost:8080/swagger-ui/index.html`

Create an order and observe how Kafka delivers the event to Payment Service and how the result updates the order status.

---

**Project Type:** Hands-on Microservices Project  
**Architecture:** Event-Driven Architecture  
**Focus:** Asynchronous Communication with Kafka and Spring Boot
