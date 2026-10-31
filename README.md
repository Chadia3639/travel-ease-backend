# TravelEase Backend

## Student Information

| Field        | Value                     |
|--------------|---------------------------|
| Name         | Uwamahoro Rwema Chadia    |
| Student ID   | 26752                     |
| Project Name | TravelEase Backend        |

## Tech Stack

- Java 23
- Spring Boot 4.1.1
- Spring Data JPA
- PostgreSQL 16

## Prerequisites

- Java 17 or newer (tested on Java 23)
- PostgreSQL running on `localhost:5432`
- Internet access (first run downloads Maven dependencies)

## Steps to Run the API

### 1. Set up PostgreSQL

Make sure PostgreSQL is running, then create the database:

```sql
CREATE DATABASE travel_ease_db;
```

Hibernate will create all tables automatically on first startup (`ddl-auto=update`).

The app connects with:
- **Host:** localhost:5432
- **Database:** travel_ease_db
- **Username:** postgres
- **Password:** 1234

If your PostgreSQL password differs, update `src/main/resources/application.properties`.

### 2. Delete old build (optional but recommended)

```bash
# Windows
rmdir /s /q target

# Mac/Linux
rm -rf target
```

### 3. Start the application

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

**Mac/Linux:**
```bash
./mvnw spring-boot:run
```

Wait for:
```
Started TravelEaseApplication
```

### 4. API Base URL

```
http://localhost:8081/api
```

### 5. Test with Postman

1. Import `travel-ease-midterm.postman_collection.json` into Postman.
2. Open the **Collection Runner**.
3. Run the entire collection **in order** against an **empty** `travel_ease_db`.
4. The `0 Seed data` folder seeds the database and stores all IDs automatically.
5. All tests should pass.

> To run again: drop and recreate `travel_ease_db`, then restart the app.

## API Endpoints

| Resource         | Base Path                  |
|------------------|----------------------------|
| Travel Interests | `/api/travel-interests`    |
| Agency Branches  | `/api/agency-branches`     |
| Travel Agents    | `/api/travel-agents`       |
| Travelers        | `/api/travelers`           |
| Trip Plans       | `/api/trip-plans`          |
| Trip Expenses    | `/api/trip-expenses`       |
| Dashboard        | `/api/dashboard`           |
