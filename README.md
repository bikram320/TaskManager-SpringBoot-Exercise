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
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- MapStruct

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

## ⚙️ Setup & Run

1. **Clone the Repository**

```bash
git clone https://github.com/bikram320/TaskManager-SpringBoot-Exercise.git
cd TaskManager-SpringBoot-Exercise
```

2. **Configure MySQL**

Create a database named `task_manager` and update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Run the Project**

```bash
./mvnw spring-boot:run
```

---

## 📥 Sample JSON Requests

### ➕ Create User

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "123456"
}
```

### ➕ Create Task

```json
{
  "name": "Create README",
  "description": "Write project documentation",
  "status": "PENDING",
  "userId": 1
}
```

### 🔁 Change Task Status

```json
{
  "previousStatus": "PENDING",
  "newStatus": "COMPLETED"
}
```

---

## 🤝 Contribution

Feel free to fork this project, raise issues, and submit PRs to improve it.

---

## 👤 Author

**Bikram Bishwokarma**  
GitHub: [@bikram320](https://github.com/bikram320)

