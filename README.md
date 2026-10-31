# TravelEase Backend

**Name:** Uwamahoro Rwema Chadia  
**Student ID:** 26752  
**Project:** TravelEase Backend

---

## What this project is

A REST API for a travel agency management system built with Spring Boot. It handles travel interests, agency branches, travel agents, travelers, trip plans, trip expenses, and a dashboard summary.

## Tech used

- Java 23
- Spring Boot 4.1.1
- Spring Data JPA
- PostgreSQL 16

## How to run it

### 1. Create the database

Make sure PostgreSQL is running on your machine, then create the database:

```sql
CREATE DATABASE travel_ease_db;
```

The app uses these connection settings:
- Host: localhost:5432
- Database: travel_ease_db
- Username: postgres
- Password: 1234

If your password is different, change it in `src/main/resources/application.properties`.

Hibernate will create all the tables automatically when the app starts.

### 2. Start the app

On Windows:
```bash
mvnw.cmd spring-boot:run
```

On Mac/Linux:
```bash
./mvnw spring-boot:run
```

Wait until you see `Started TravelEaseApplication` in the console.

The API runs on: `http://localhost:8081/api`

### 3. Test with Postman

Import `travel-ease-midterm.postman_collection.json` into Postman and run the collection in order starting from the `0 Seed data` folder. That folder seeds the database and saves all the IDs as variables automatically. All requests should pass on a fresh empty database.

To run again from scratch: drop and recreate `travel_ease_db`, then restart the app.

## Endpoints

| Resource | Path |
|----------|------|
| Travel Interests | `/api/travel-interests` |
| Agency Branches | `/api/agency-branches` |
| Travel Agents | `/api/travel-agents` |
| Travelers | `/api/travelers` |
| Trip Plans | `/api/trip-plans` |
| Trip Expenses | `/api/trip-expenses` |
| Dashboard | `/api/dashboard` |
