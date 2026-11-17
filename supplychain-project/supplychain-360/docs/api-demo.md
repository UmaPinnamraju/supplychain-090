# API demo sequence

1. Login:
```http
POST /api/auth/login
Content-Type: application/json

{"username":"admin","password":"Admin@123"}
```

2. Copy the `token` and send `Authorization: Bearer <token>`.

3. Inspect inventory:
```http
GET /api/inventory
```

4. Create an order:
```http
POST /api/orders
Content-Type: application/json

{
  "customerName": "Northwind Distribution",
  "customerEmail": "ops@northwind.example",
  "shippingAddress": "100 Market Street, Philadelphia, PA",
  "lines": [
    {"sku":"PUMP-100","quantity":2},
    {"sku":"VALVE-200","quantity":5}
  ]
}
```

5. Inspect the order and shipment:
```http
GET /api/orders
GET /api/shipments
```

6. Update shipment:
```http
PATCH /api/shipments/1/status
Content-Type: application/json

{"status":"IN_TRANSIT","notes":"Departed regional distribution center"}
```

Use Swagger UI for interactive exploration.
