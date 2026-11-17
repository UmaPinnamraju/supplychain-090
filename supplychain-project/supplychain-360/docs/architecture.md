# Architecture and design decisions

## Bounded contexts

- **Order Management:** order header, line items, lifecycle status and cancellation.
- **Inventory:** stock levels, available quantity and reservation.
- **Supplier Management:** supplier master data and replenishment contacts.
- **Shipment Management:** shipment creation, carrier/tracking reference and milestones.
- **Platform:** authentication, authorization, audit events and operational KPIs.

## Order lifecycle

`CREATED -> CONFIRMED -> FULFILLING -> SHIPPED -> DELIVERED`

A cancellation is allowed before shipment. Cancellation releases the reserved stock.

## Event-driven flow

The order transaction commits first. An after-commit event listener publishes `OrderCreatedEvent` to Kafka. A consumer creates a shipment if one does not already exist for the order. In production, use the transactional outbox pattern to close the crash window between database commit and broker publication, plus retry topics and a dead-letter queue.

## Data integrity

- SQL Server foreign keys protect order lines and shipment references.
- Inventory rows are locked during reservation.
- Quantities must be positive.
- Shipment creation is idempotent by unique order reference.
- Every shipment status update creates an audit entry.

## Security

JWT bearer authentication with ADMIN, OPERATOR and VIEWER roles. The secret is configured by environment variable. The seeded credentials are for local demonstration only.
