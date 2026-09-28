# Product Pagination API

A Spring Boot REST API for browsing products with **pagination, sorting, and filtering**, backed by **PostgreSQL** and database migrations managed by **Flyway**.

> This is my first project using Flyway for database schema versioning.

## Tech Stack

- Java 17
- Spring Boot 4 (Web MVC, Data JPA)
- PostgreSQL 15 (via Docker)
- Flyway (database migrations)
- Lombok
- Maven

## Features

- Paginated and sortable product listing
- Filter by category, price range, and stock availability
- Case-insensitive search by product name
- Versioned schema and seed data using Flyway migrations
- Hibernate `ddl-auto=validate` — the schema is owned by Flyway, not Hibernate

## Getting Started

### Prerequisites

- Java 17+
- Docker

### 1. Start PostgreSQL

```bash
docker compose up -d
```

This starts a PostgreSQL container (`ecommerce_db`) on port `5432` with database `ecommercedb`.

### 2. Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On startup, Flyway automatically applies the migrations in `src/main/resources/db/migration`.

## Flyway Migrations

| Version | File | Description |
|---------|------|-------------|
| V1 | `V1__create_product_table.sql` | Creates the `product` table |
| V2 | `V2__insert_dummy_product.sql` | Inserts sample product data |

To change the schema, add a new file such as `V3__add_column.sql` — never edit a migration that has already been applied.

## API Endpoints

Base URL: `http://localhost:8080/api/products`

| Method | Endpoint | Required Params | Description |
|--------|----------|-----------------|-------------|
| GET | `/api/products` | — | Get all products |
| GET | `/api/products/category` | `category` | Filter by category |
| GET | `/api/products/price-range` | `minPrice`, `maxPrice` | Filter by price range |
| GET | `/api/products/in-stock` | — | Get products in stock |
| GET | `/api/products/search` | `keyword` | Search by name |

### Pagination & Sorting Params (all endpoints)

| Param | Default | Description |
|-------|---------|-------------|
| `page` | `0` | Page number (zero-based) |
| `size` | `10` | Items per page |
| `sortBy` | `id` | Field to sort by |
| `sortDir` | `asc` | `asc` or `desc` |

### Example Requests

```bash
curl "http://localhost:8080/api/products?page=0&size=5&sortBy=price&sortDir=desc"
curl "http://localhost:8080/api/products/category?category=Electronics"
curl "http://localhost:8080/api/products/price-range?minPrice=50&maxPrice=200"
curl "http://localhost:8080/api/products/search?keyword=chair"
```

### Example Response

```json
{
  "content": [
    {
      "id": "b3f1c2e4-...",
      "name": "Wireless Noise-Canceling Headphones",
      "price": 299.99,
      "category": "Electronics",
      "brand": "AudioTech",
      "inStock": true
    }
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 5,
  "totalPages": 1,
  "lastPage": true
}
```

## Project Structure

```
src/main/java/com/product_pagination
├── constant/      # Default pagination values
├── controller/    # REST endpoints
├── dto/           # PagedResponse wrapper
├── entity/        # Product JPA entity
├── repository/    # Spring Data JPA repository
└── service/       # Business logic + Pageable builder
```
