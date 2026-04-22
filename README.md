

# 🐶 Dogs API — Backend

This is a simple REST API to manage dog breeds and their sub-breeds.
It is built using **Java 17, Spring Boot, and PostgreSQL** and supports basic CRUD operations (Create, Read, Update, Delete).

The application is deployed using Docker on Render.

---

## 🔗 Links

* **Live API**
  [https://dogs-api-y5cx.onrender.com/api/dogs](https://dogs-api-y5cx.onrender.com/api/dogs)

* **GitHub Repository**
  [https://github.com/Krishnamohanvaddi/dogs-api](https://github.com/Krishnamohanvaddi/dogs-api)

---

## 📌 Overview

This API is the backend for a dog breed management system.

It allows you to:

* Add new dog breeds
* View all breeds
* Search for a breed
* Update breed details
* Delete a breed

All data is stored in a PostgreSQL database, so changes are saved even after refresh, restart, or redeployment.

---

## ⚙️ Tech Stack

* **Java 17**
* **Spring Boot 4**
* **PostgreSQL**
* **Maven**
* **Docker**
* **Render (for deployment)**

---

## 📡 API Endpoints

Base URL: `/api/dogs`

---

### 1. Get all breeds

```
GET /api/dogs
```

Returns all dog breeds.

**Example response:**

```json
[
  { "id": 1, "breed": "labrador", "subBreeds": "" },
  { "id": 2, "breed": "bulldog", "subBreeds": "boston,french" }
]
```

---

### 2. Get breed by name

```
GET /api/dogs/{breed}
```

Example:

```
GET /api/dogs/poodle
```

* Returns the breed if found
* Returns **404** if not found

---

### 3. Add a new breed

```
POST /api/dogs
```

**Request body:**

```json
{
  "breed": "labrador",
  "subBreeds": ""
}
```

* Returns **201 Created** on success
* Returns **409 Conflict** if breed already exists

---

### 4. Update a breed

```
PUT /api/dogs/{id}
```

Example:

```
PUT /api/dogs/3
```

**Request body:**

```json
{
  "breed": "poodle",
  "subBreeds": "miniature,standard,toy,teacup"
}
```

* Returns updated data
* Returns **404** if ID not found

---

### 5. Delete a breed

```
DELETE /api/dogs/{id}
```

* Returns **204** if deleted
* Returns **404** if ID not found

---

## 💻 Run Locally

### Prerequisites

* Java 17
* Maven
* PostgreSQL

---

### Steps

1. Clone the project

```bash
git clone https://github.com/krishnamohanvaddi/dogs-api.git
cd dogs-api
```

2. Create database

```sql
CREATE DATABASE dogs;
```

3. Update database config
   Go to:

```
src/main/resources/application.properties
```

Update:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dogsdb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
```

---

4. Run the app

```bash
./mvnw spring-boot:run
```

---

5. Test it
   Open:

```
http://localhost:8080/api/dogs
```

---

## 🚀 Deployment (Render)

This project is deployed on Render using Docker.

### What I did:

* Pushed code to GitHub
* Created PostgreSQL database in Render
* Created a web service using Docker
* Added environment variables
* Enabled auto-deploy

⚠️ Note:
Render free tier sleeps after some time.
First request may take ~30–60 seconds.

---

## 🔑 Environment Variables

Set these in Render:

* `DATABASE_URL`
* `DATABASE_USERNAME`
* `DATABASE_PASSWORD`
* `PORT`

---

## 🧠 Simple Design Decision

* I stored sub-breeds as a **comma-separated string** instead of creating a separate table
* This keeps things simple for this use case

---

## 🔮 Improvements (if extended)

If I continue this project, I would:

* Add pagination for large data
* Restrict CORS properly
* Add authentication and security

---


