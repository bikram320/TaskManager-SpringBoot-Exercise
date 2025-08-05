# 📝 TaskManager - Spring Boot Exercise

A simple Task Manager REST API built with **Spring Boot** and **MySQL**. It provides endpoints to manage users and their tasks, including task creation, status updates, and user password management.
(just a practice code , not a project)
---

## 🚀 Features

### 👤 User Management
- Create new users
- Get all users or a specific user by ID
- Update user information
- Change user password with old password verification
- Delete users

### ✅ Task Management
- Create new tasks
- Get all tasks or filter tasks by user ID
- Update task status with previous status validation

---

## 🧰 Tech Stack

- Java 17+
- Spring Boot
- Spring Secuirty
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- MapStruct

---

## 🔐 Security (JWT)

This practice project includes basic stateless authentication using **JWT (JSON Web Tokens)** with the following flow:

- `POST /auth/login`:  
  Authenticates user credentials and returns an **Access Token** in the response body and a **Refresh Token** in a secure, HTTP-only cookie.

- `GET /auth/refresh`:  
  Accepts the Refresh Token (from cookie) and issues a new Access Token if valid.

- `GET /auth/CurrentUser`:  
  Returns the authenticated user’s data based on the Access Token provided in the `Authorization` header.

**Token Details:**
- **Access Token:** Short-lived (e.g., 5 minutes), included in `Authorization: Bearer <token>` header.
- **Refresh Token:** Long-lived (e.g., 1 hour), stored in `HttpOnly` cookie for security.
- **BCrypt Password Encoding** used for secure password storage.

---

## 📦 API Endpoints

### 👤 User Endpoints (`/users`)

| Method | Endpoint                          | Description                     |
|--------|-----------------------------------|---------------------------------|
| GET    | `/users`                          | Get all users                   |
| GET    | `/users/{id}`                     | Get user by ID                  |
| POST   | `/users`                          | Create a new user               |
| PUT    | `/users/{id}`                     | Update user                     |
| POST   | `/users/change-password/{id}`     | Change user password            |
| DELETE | `/users/{id}`                     | Delete user                     |

---

### ✅ Task Endpoints (`/tasks`)

| Method | Endpoint                          | Description                     |
|--------|-----------------------------------|---------------------------------|
| GET    | `/tasks`                          | Get all tasks                   |
| GET    | `/tasks/user/{userId}`            | Get tasks by user ID            |
| POST   | `/tasks`                          | Create a new task               |
| POST   | `/tasks/{id}/change-status`       | Change task status              |

---

## 🤝 Contribution

Feel free to fork this project, raise issues, and submit PRs to improve it.

---

## 👤 Author

**Bikram Bishwokarma**  
GitHub: [@bikram320](https://github.com/bikram320)

