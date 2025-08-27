# E-commerce application

This is a Spring Boot backend microservices application that I built to practice and demonstrate my ability to implement modern best practices and technologies.

## Architecture Overview

The project includes a standard e-commerce workflow:

- Admin can create new `courses` and `authors`.
- A user can create an `order`, which reserves a spot in a course.
- A user can pay for the order, and if the payment is successful, the system sends an email.

Below is a detailed description of each service.

### API

Provides shared definitions and classes for communication between microservices.  
It contains:

- `.proto` files defining the gRPC contracts.
- Auto-generated gRPC classes.
- DTO classes for serialization and deserialization.

### Catalog

Handles all operations related to `authors` and `courses`. It stores data in PostgreSQL and for GET operations caches data in Redis. These are the API endpoints:

- `POST /api/rest/author/create` – allows an admin to create an `author`.
- `POST /api/rest/course/create` – allows an admin to create a `course`.
- `GET /api/rest/course/all` – allows users to retrieve all available courses.

### Order

Includes all operations to create and validate `orders`. After a user sends a request to create an `order`, the service sends a gRPC request to the **Catalog** service to validate the order information. If the validation is successful, the order is saved in the PostgreSQL database.

These are the API endpoints:

- `POST /api/rest/order/create` – allows a user to create an order.
- `GET /api/rest/order/get/{id}` – allows a user to retrieve an order by ID.

### Payment

Handles the payment operation. A user sends a request to pay for an order, and the **Payment** service sends a gRPC request to the **Order** service to validate the payment information. If it is valid, a Kafka message is sent to the **Message** and **Order** services. The **Message** service sends an email about the successful payment, and the **Order** service updates the status of the order to `PAID`.

- `POST /api/rest/payment` – allows a user to pay for an order.

### Message

Receives Kafka messages from the **Payment** service and sends an email via Gmail SMTP.

### Gateway Server

The service exposes a port to communicate with all REST API endpoints from external clients.  
It also provides JWT validation, a circuit breaker, and a retry mechanism for all microservices.

## Technical stack

- **Spring Framework** – a powerful and widely used framework for building scalable Java web applications.  
  This project leverages key Spring modules, including Spring Boot, Spring Web, Spring Data, and Spring Cloud.
- **PostgreSQL** – a database for storing `orders` and `courses`.
- **Redis** – a caching system for storing `courses` information.
- **Kafka** – a broker for asynchronous messaging between services.
- **gRPC** – a high-performance RPC framework that enables efficient synchronous communication between microservices.
- **Docker** – a platform for building and running containerized applications; this project uses the Buildpacks plugin to create images.
- **Kubernetes** – a container orchestration system that manages deployment, scaling, load balancing, and service discovery.
- **Helm** – a package manager for Kubernetes that simplifies the deployment and management of applications.
- **OAuth2** – an open standard for secure authentication and authorization, used here with JWT validation.
- **Keycloak** – an identity and access management solution that provides user authentication, registration, and authorization.

## Deployment

The e-commerce application is deployed on Kubernetes using Helm charts. The `helm/` folder in the project contains all the necessary Helm-related files.  
It’s necessary to install dependencies first: PostgreSQL, Redis, Kafka, and Keycloak. For this purpose, Bitnami Helm charts are used.

```bash
#PostgreSQL
helm install db bitnami/postgresql --set auth.database=appdb --set auth.username=appuser --set auth.password=supersecret
```

```bash
#Redis
helm install cache bitnami/redis --set architecture=standalone --set auth.password=redissecret
```

```bash
#Kafka
helm install my-kafka bitnami/kafka -f values.yaml
```

```bash
#Keycloak
helm install my-keycloak bitnami/keycloak -f values.yaml
```

After installing dependencies, you can deploy the microservices using their Helm charts:

```bash
helm install catalog ./catalog
```

## Contact

If you have any questions, feedback or proposals, feel free to reach out:

- Email: ilyakonst95@gmail.com
- LinkedIn: [Ilya Kanstanchuk](https://www.linkedin.com/in/ilya-kanstanchuk-b0b332305/)
- Telegram: @tdespairedt
