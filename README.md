# Expense Tracker API

Spring Boot REST API practice project for managing each authenticated user's expenses and categories.

## Current progress

- PostgreSQL Docker Compose and application configuration
- User, Category, and Expense entities with JPA relationships
- BigDecimal amounts and LocalDate expense dates

Next: repositories, JWT authentication, category and expense endpoints, ownership checks, filtering, and totals.

## Development setup

Requires Java 17 or later and Docker. Create a local `.env` file (excluded from Git):

```properties
POSTGRES_URL=expense_tracker
POSTGRES_USER=expense_tracker
POSTGRES_PASSWORD=replace_with_your_local_password
```

`POSTGRES_URL` is the database name. PostgreSQL is exposed on localhost port 5433.

Run on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Authentication and API endpoints are still under development.
