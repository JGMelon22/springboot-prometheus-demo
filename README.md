# Spring Boot Observability Example

A simple Web REST API built with Spring Boot 4.0 and Java 21 demonstrating observability best practices using Prometheus for metrics collection and Grafana for visualization, all orchestrated with Docker and Docker Compose.

## Overview

This project showcases how to implement comprehensive observability in a Spring Boot application, including metrics, monitoring, and visualization. It provides a production-ready setup for tracking application performance and health.

## Features

- REST API endpoints with Spring Boot 4.0
- Prometheus metrics exposure via Actuator
- Grafana dashboards for real-time visualization
- Docker containerization for all services
- Docker Compose orchestration for easy deployment
- Custom application metrics
- JVM and system metrics monitoring

## Prerequisites

Before running this project, ensure you have the following installed:

- Java 21 or higher
- Docker Desktop or Docker Engine
- Docker Compose
- Maven (for building the application)

## Project Structure

```
springboot-prometheus-demo/
├── monitoring/
│   ├── grafana/
│   │   ├── provisioning/
│   │   │   └── datasources/
│   │   │       └── datasources.yml
│   └── prometheus/
│       └── prometheus.yml
├── src/
│   ├── main/
│   │   ├── java/com/jgmelon22/prometheus_example/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── PrometheusExampleApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd spring-boot-observability-example
```

### 2. Build the Application

```bash
mvn clean package
```

### 3. Start the Services

```bash
docker-compose up -d
```

This will start three services:
- **Spring Boot Application** - Port 8080
- **Prometheus** - Port 9090
- **Grafana** - Port 3000

### 4. Access the Services

- **Application API**: http://localhost:8080/api/products
- **Actuator Endpoints**: http://localhost:8080/actuator
- **Prometheus Metrics**: http://localhost:8080/actuator/prometheus
- **Prometheus UI**: http://localhost:9090
- **Grafana Dashboard**: http://localhost:3000 (default credentials: admin/admin)

### 5. Test the API

You can test the Product API using curl:

```bash
# Get all products
curl http://localhost:8080/api/products

# Get a product by ID
curl http://localhost:8080/api/products/1

# Create a new product
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Sample Product","price":99.99,"description":"A sample product"}'

# Update a product
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Product","price":149.99,"description":"Updated description"}'

# Delete a product
curl -X DELETE http://localhost:8080/api/products/1
```

## Configuration

### Application Metrics

The application exposes metrics through Spring Boot Actuator. Key endpoints include:

- `/actuator/health` - Health check endpoint
- `/actuator/metrics` - Available metrics
- `/actuator/prometheus` - Prometheus-formatted metrics

### Prometheus Configuration

Prometheus is configured to scrape metrics from the Spring Boot application at regular intervals. The configuration is located in `monitoring/prometheus/prometheus.yml`.

### Grafana Setup

Grafana is pre-configured with Prometheus as a data source through the provisioning files in `monitoring/grafana/provisioning/datasources/`.

1. Log in to Grafana at http://localhost:3000 (default credentials: admin/admin)
2. The Prometheus data source is automatically configured
3. Import dashboards or create custom ones to visualize your metrics

## Available Endpoints

### Product API Endpoints

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get a product by ID
- `POST /api/products` - Create a new product
  ```json
  {
    "name": "Product Name",
    "price": 99.99,
    "description": "Product Description"
  }
  ```
- `PUT /api/products/{id}` - Update an existing product
  ```json
  {
    "name": "Updated Name",
    "price": 149.99,
    "description": "Updated Description"
  }
  ```
- `DELETE /api/products/{id}` - Delete a product

### Actuator Endpoints

- `GET /actuator/health` - Application health status
- `GET /actuator/info` - Application information
- `GET /actuator/metrics` - List of available metrics
- `GET /actuator/prometheus` - Prometheus-formatted metrics

## Monitoring Metrics

The application tracks various metrics including:

- **HTTP Metrics**: Request counts, response times, and status codes for `/api/products` endpoints
- **JVM Metrics**: Memory usage, garbage collection, and thread statistics
- **Database Metrics**: Connection pool statistics and query performance
- **Custom Business Metrics**: Product creation, updates, and deletion counts
- **System Metrics**: CPU usage, disk I/O, and system load

Example metrics available:
- `http_server_requests_seconds` - HTTP request durations
- `jvm_memory_used_bytes` - JVM memory usage
- `hikaricp_connections_active` - Active database connections
- `product_operations_total` - Custom product operation counters

## Development

### Running Locally Without Docker

```bash
mvn spring-boot:run
```

### Running Tests

```bash
mvn test
```

### Building Docker Image

```bash
docker build -t spring-boot-observability .
```

## Grafana Dashboard Examples

Consider importing these community dashboards:
- **JVM (Micrometer)** - Dashboard ID: 4701
- **Spring Boot Statistics** - Dashboard ID: 6756

## Technologies Used

- **Spring Boot 4.0** - Application framework
- **Java 21** - Programming language
- **Micrometer** - Metrics instrumentation
- **Prometheus** - Metrics collection and storage
- **Grafana** - Metrics visualization
- **Docker** - Containerization
- **Docker Compose** - Service orchestration

## Resources

- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [Prometheus Documentation](https://prometheus.io/docs/)
- [Grafana Documentation](https://grafana.com/docs/)
- [Micrometer Documentation](https://micrometer.io/docs)
- [Micrometer Documentation]Building a CRUD Application with Spring Boot and REST API)
- [Monitoring Made Simple: Empowering Spring Boot Applications with Prometheus and Grafana](https://medium.com/simform-engineering/revolutionize-monitoring-empowering-spring-boot-applications-with-prometheus-and-grafana-e99c5c7248cf)