# 🐾🐶 Dogs API — Backend

 A RESTful API's for managing dog breeds and sub-breeds, built with **Java 17**, **Spring Boot 4**, and **PostgreSQL**. Supports full CRUD operations. Deployed via Docker on Render.

---

## 🔗 Live URLs

| Resource | URL |
|---|---|
| **Live API** | `https://dogs-api-y5cx.onrender.com/api/dogs` |
| **GitHub Repo** | `https://github.com/Krishnamohanvaddi/dogs-api` |

---

## Overview

This API serves as the backend for a dog breed management system. It exposes HTTP endpoints that allow a client to **create**, **read**, **update**, and **delete** dog breeds, along with their associated sub-breeds.

All data changes persist to a **PostgreSQL** database, meaning changes survive browser refreshes, server restarts, and redeployments.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.5 |
| Database | PostgreSQL 15 |
| Build tool | Maven  |
| Containerisation | Docker (eclipse-temurin:17) |
| Deployment | Render (Docker runtime) |

---

## API Endpoints

Base URL: `/api/dogs`

### Get all breeds

```
GET /api/dogs
```

Returns a list of all dog breeds in the database.

**Response `200 OK`:**
```json
[
  { "id": 1, "breed": "labrador", "subBreeds": "" },
  { "id": 2, "breed": "bulldog",  "subBreeds": "boston,french" },
  { "id": 3, "breed": "poodle",   "subBreeds": "miniature,standard,toy" }
]
```

---

### Get breed by name

```
GET /api/dogs/{breed}
```

Fetches a single breed by its name.

**Example:** `GET /api/dogs/poodle`

**Response `200 OK`:**
```json
{
  "id": 3,
  "breed": "poodle",
  "subBreeds": "miniature,standard,toy"
}
```

**Response `404 Not Found`** — if the breed does not exist.

---

### Create a breed

```
POST /api/dogs
Content-Type: application/json
```

**Request body:**
```json
{
  "breed": "labrador",
  "subBreeds": ""
}
```

**Response `201 Created`:**
```json
{
  "id": 84,
  "breed": "labrador",
  "subBreeds": ""
}
```

**Response `409 Conflict`** — if the breed already exists.

---

### Update a breed

```
PUT /api/dogs/{id}
Content-Type: application/json
```

**Example:** `PUT /api/dogs/3`

**Request body:**
```json
{
  "breed": "poodle",
  "subBreeds": "miniature,standard,toy,teacup"
}
```

**Response `200 OK`** — returns the updated breed object.

**Response `404 Not Found`** — if the ID does not exist.

---

### Delete a breed

```
DELETE /api/dogs/{id}
```

**Example:** `DELETE /api/dogs/3`

**Response `204 No Content`** — breed successfully deleted.

**Response `404 Not Found`** — if the ID does not exist.

---

## Running Locally

### Prerequisites

- Java 17 ([Adoptium Temurin](https://adoptium.net))
- Maven (or use the included `./mvnw` wrapper)
- PostgreSQL 15 running locally

### 1. Clone the repository

```bash
git clone https://github.com/krishnamohanvaddi/dogs-api.git
cd dogs-api
```

### 2. Create a local PostgreSQL database

```sql
CREATE DATABASE dogs;
```

### 3. Configure local properties

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dogsdb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
server.port=8080
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

### 5. Verify

Open `http://localhost:8080/api/dogs` in your browser — you should see all breeds returned as JSON.

---

## Deployment on Render

This service is deployed on [Render](https://render.com) using its **Docker runtime**.

### Steps followed

1. Pushed code (including `Dockerfile`) to GitHub
2. Created a **PostgreSQL** database on Render (free plan)
3. Created a **Web Service** on Render — connected to the GitHub repo, runtime set to **Docker**
4. Added the three environment variables listed below
5. Render auto-deploys on every push to `main`

> **Note:** Render's free web service spins down after 15 minutes of inactivity. The first request after a sleep period may take up to 60 seconds. Subsequent requests respond normally.

---

## Environment Variables

These must be set in the Render dashboard (or your local environment):

| Variable | Description | Example |
|---|---|---|
| `DATABASE_URL` | JDBC connection string to PostgreSQL | `jdbc:postgresql://host/dbname` |
| `DATABASE_USERNAME` | Database username | `dogs_db_user` |
| `DATABASE_PASSWORD` | Database password | `supersecretpassword` |
| `PORT` | Port the server listens on | `8080` |

---
