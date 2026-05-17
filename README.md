# BazaarEdge: E-Commerce Backend

A Java Spring Boot backend for an e-commerce platform (renamed from the original project). This README follows the style and structure of the provided sample README and is tailored to this codebase.

## 🚀 Overview

BazaarEdge is a lightweight, production-oriented backend meant for building and running an e-commerce service. It includes REST APIs for products and categories, integrations with payment gateways (Stripe and Razorpay), Redis caching, and JPA-based persistence.

Package root: `com.scalers.productserviceebatch`

## 📌 Features & Tech Stack

- Java 17+ / 18 (project compiled with Java 18 here)
- Spring Boot (auto-configuration, Web, Data JPA)
- Hibernate / JPA for persistence
- MySQL (production), H2 (tests)
- Redis caching (Lettuce recommended)
- Payment gateway integrations: Stripe & Razorpay
- Maven build (mvn or wrapper)

## 🎯 Design & Best Practices

- Clean layered architecture: controllers → services → repositories → models
- Interfaces and abstractions: `PaymentGateway`, `PaymentService`, `ProductService`, `CategoryService`
- Global exception handling via `advice/ControllerAdvice.java`
- Auditing support via `configs/AuditConfig.java`

## 📂 Project Structure (key files)

src/main/java/com/scalers/productserviceebatch
- `ProductServiceEBatchApplication.java` (main)
- advice/
  - `ControllerAdvice.java`
- configs/
  - `RedisTemplateConfig.java` (Redis configuration)
  - `RestTemplateConfig.java` (external API client)
  - `AuditConfig.java`
- controllers/
  - `ProductController.java`
  - `CategoryController.java`
  - `PaymentController.java`
- dtos/
  - `PaymentRequestDTO.java`, `ErrorDTO.java`, `FakeStore*DTOs`
- exceptions/
  - `ProductNotFoundException.java`, `CategoryNotFoundException.java`
- models/
  - `Product.java`, `Category.java`, `BaseModel.java` (+ copy files)
- paymentgateway/
  - `PaymentGateway.java`, `StripePaymentGateway.java`, `RazorpayPaymentGateway.java`
- repository/
  - `ProductRepository.java`, `CategoryRepository.java`, `projections/`
- services/
  - `ProductService.java`, `CategoryService.java`, `PaymentService.java`, and service implementations

(Full tree available in the project workspace.)

## 📦 Prerequisites

- Java 17/18 installed
- MySQL server (or use AWS RDS)
- Redis server (optional but recommended for caching)
- Maven or use the Maven wrapper included in the project

## 🔧 Configuration

Edit `src/main/resources/application.properties` to set your production/dev database and redis configuration. Example minimal MySQL properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scaler
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

spring.redis.host=localhost
spring.redis.port=6379
```

Notes:
- `scaler` in the JDBC URL is the database name — create it in MySQL (`CREATE DATABASE scaler;`) or change the name.
- If you don't want to run MySQL locally, configure an RDS endpoint or use H2 for lightweight local tests.

## 🔁 How to run locally

From the project root (Windows PowerShell):

```powershell
# using the Maven wrapper (recommended if you don't have Maven installed)
.\mvnw.cmd clean package
.\mvnw.cmd spring-boot:run

# or, if Maven is installed system-wide
mvn clean package
mvn spring-boot:run
```

The app starts on `http://localhost:8080` by default (or the port configured in `application.properties`).

## 🧪 Running tests

This project contains `@SpringBootTest` tests that by default require a datasource. To run tests reliably without connecting to your local MySQL server, use an H2 in-memory datasource for test scope.

1. Add (if not already present) to `pom.xml` under `<dependencies>`:

```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>test</scope>
</dependency>
```

2. Create `src/test/resources/application.properties` with the test DB settings:

```properties
spring.datasource.url=jdbc:h2:mem:scaler;DB_CLOSE_DELAY=-1
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
```

3. Run tests with the Maven wrapper (PowerShell):

```powershell
.\mvnw.cmd -q test
```

Or with installed Maven:

```powershell
mvn -q test
```

If `mvn` is not recognized on your machine, prefer the wrapper `.\mvnw.cmd` which is bundled with the project.

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---:|---|
| POST | `/product` | Create a new product |
| PUT  | `/product/{id}` | Update a product (product's category must exist) |
| GET  | `/product/{id}` | Get product by ID |
| GET  | `/products` | Paginated product listing (query params: pageNumber, pageSize, fieldName) |
| POST | `/payments` | Create a payment link (Stripe/Razorpay) |
| POST | `/webhook` | Payment webhook callback |
| POST | `/category` | Create a category |
| GET  | `/category/{id}` | Get category by ID |

## 🧾 Sample payloads

- Create Category (POST `/category`):

```json
{
  "title": "Accessories"
}
```

- Update Product (PUT `/product/{id}`) — category must exist and be referenced by id:

```json
{
  "id": 1,
  "title": "Wireless Mouse",
  "description": "Ergonomic wireless mouse with 2.4GHz connectivity and long battery life.",
  "price": 25.99,
  "category": { "id": 3, "title": "Accessories" },
  "imageUrl": "https://example.com/images/wireless-mouse.jpg"
}
```

Create the category first (POST `/category`) and use the returned `id` in the product payload.

## ⚙️ Payment gateway notes

- Stripe and Razorpay API keys must be set in `application.properties` or provided through environment variables.
- Stripe integration should set the API key once before all Stripe operations; do not switch keys between calls when creating related resources.


## Additional Project Links:
- Email Service and Kafka :  https://github.com/pychand/ScalerBackendEmailService
- User Service and OAuth : https://github.com/pychand/scalerbackendprojectkafkaoauth

## 🛠 Troubleshooting & Tips

- If you see `mvn: The term 'mvn' is not recognized`, run the Maven wrapper `.\mvnw.cmd` or install Maven and add it to your PATH.
- If Hibernate complains about dialect or driver: ensure the JDBC driver dependency (`com.mysql:mysql-connector-j`) is present in `pom.xml` and `application.properties` has the correct `spring.datasource.*` entries.
- For Redis issues, add `spring-boot-starter-data-redis` to `pom.xml` and prefer `LettuceConnectionFactory` (no extra client dependency needed).
- Keep secrets out of source control — use environment variables or externalized configuration.
