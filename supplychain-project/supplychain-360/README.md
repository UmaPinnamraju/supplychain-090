# SupplyChain 360

Enterprise-style order, inventory, supplier and shipment management platform.

**Stack:** Java 17, Spring Boot 3, Spring Security, Spring Data JPA/Hibernate, Microsoft SQL Server, Apache Kafka, Angular 18, Docker Compose, GitHub Actions.

## Business workflow

1. Create an order from the Angular operations console.
2. The Order API validates the request and checks available inventory.
3. Inventory is reserved transactionally.
4. `OrderCreatedEvent` is published to Kafka.
5. The shipment workflow consumes the event and creates a shipment.
6. Operations users can update shipment milestones and inspect the audit trail.
7. Low-stock items can be associated with suppliers for replenishment planning.

This is a portfolio implementation, not a production-certified logistics platform. The included demo authentication uses seeded users and a development JWT secret. Replace secrets, add migrations, harden authorization and run security/load testing before production use.

## Architecture

```text
Angular 18 UI
    |
    | REST + JWT
    v
Spring Boot API
    |-- Auth / RBAC
    |-- Orders
    |-- Inventory reservation
    |-- Suppliers
    |-- Shipments / audit
    |
    +---- Microsoft SQL Server (transactional data)
    |
    +---- Kafka topic: supplychain.order-created
                    |
                    +-- Shipment event consumer
```

## Repository layout

```text
supplychain-360/
├── backend/                 Spring Boot REST API
├── frontend/                Angular standalone application
├── database/                SQL Server initialization scripts
├── infrastructure/          Docker Compose and Dockerfiles
├── docs/                    Architecture, API and demo guide
└── .github/workflows/       CI pipeline
```

## Run locally with Docker

Prerequisites: Docker Desktop with at least 6 GB RAM available.

```bash
docker compose -f infrastructure/docker-compose.yml up --build
```

Services:
- Angular UI: http://localhost:4200
- API: http://localhost:8080/api
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- SQL Server: localhost:1433
- Kafka: localhost:9092

Demo users:
- `admin` / `Admin@123` (ADMIN)
- `operator` / `Operator@123` (OPERATOR)
- `viewer` / `Viewer@123` (VIEWER)

Login endpoint: `POST /api/auth/login` with `{"username":"admin","password":"Admin@123"}`.

## Run backend without Docker

1. Start SQL Server and Kafka locally.
2. Create database `supplychain360` (or let the configured SQL Server user create it).
3. From `backend/` run:

```bash
mvn spring-boot:run
```

For tests:

```bash
mvn test
```

## Run frontend without Docker

```bash
cd frontend
npm install
npm start
```

The Angular dev server proxies `/api` to `http://localhost:8080`.

## Useful API endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/auth/login` | Obtain JWT |
| GET | `/api/dashboard/summary` | KPI summary |
| GET/POST | `/api/orders` | List/create orders |
| GET | `/api/orders/{id}` | Order detail |
| POST | `/api/orders/{id}/cancel` | Cancel order and release stock |
| GET | `/api/inventory` | Inventory list |
| PATCH | `/api/inventory/{id}/adjust` | Adjust on-hand quantity |
| GET/POST | `/api/suppliers` | Supplier management |
| GET | `/api/shipments` | Shipment tracking |
| PATCH | `/api/shipments/{id}/status` | Update shipment milestone |

## Suggested GitHub presentation

1. Create a repository named `supplychain-360`.
2. Extract this ZIP and push the contents to the repository root.
3. Add screenshots of the dashboard, order creation and shipment tracking.
4. In the repository About section use:  
   `Event-driven supply chain platform built with Java, Spring Boot, Angular, SQL Server and Kafka.`
5. Keep the README honest: describe this as a portfolio implementation and only claim performance metrics after benchmarking it yourself.

## Important implementation notes

- Inventory reservation uses a database transaction and pessimistic row locking to reduce overselling risk.
- Order creation publishes a Kafka event after the database transaction commits.
- The event consumer is idempotent at the shipment level by checking the order reference.
- The demo uses `ddl-auto=update` for convenience. For a real deployment, replace it with Flyway or Liquibase migrations.
- The included Docker Compose file is intended for local development.

## Before publishing

- [ ] Run the complete stack locally and verify login, order creation and shipment creation.
- [ ] Capture genuine screenshots from your running application.
- [ ] Add a short architecture diagram or draw.io export.
- [ ] Add integration tests for the SQL Server + Kafka workflow.
- [ ] Replace development passwords and JWT secret before any hosted deployment.
