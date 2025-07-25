# Learning Management System API

A Spring Boot-based RESTful API for managing students, instructors, courses, and enrollments.

---

## Base URL

```
http://localhost:8080/api/v1
```

---

## 📘 API Endpoints

---

### 🧑‍🎓 Students

**Base Path:** `/api/v1/students`

| Method | Endpoint                    | Description                         | Status Codes        |
|--------|-----------------------------|-------------------------------------|---------------------|
| GET    | `/get`                      | Get all students                    | 200 OK              |
| POST   | `/add`                      | Add a new student                   | 200 OK, 400 Bad Request |
| PUT    | `/update/{id}`              | Update student by ID                | 200 OK, 400 Bad Request |
| DELETE | `/delete/{id}`              | Delete student by ID                | 200 OK, 400 Bad Request |
| GET    | `/get-by-major/{major}`     | Get students by major               | 200 OK, 404 Not Found |
| GET    | `/get-highest-gpa`          | Get student with highest GPA        | 200 OK, 404 Not Found |

#### Request/Response Examples

**Add Student:**
```http
POST /api/v1/students/add
Content-Type: application/json

{
  "id": "STD001",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "major": "Computer Science",
  "gpa": 3.75
}
```

**Response:**
```json
{
  "message": "Student added successfully"
}
```

---

### 👨‍🏫 Instructors

**Base Path:** `/api/v1/instructors`

| Method | Endpoint                                        | Description                                     | Status Codes           |
|--------|------------------------------------------------|-------------------------------------------------|------------------------|
| GET    | `/get`                                         | Get all instructors                             | 200 OK                 |
| POST   | `/add`                                         | Add a new instructor                            | 200 OK, 400 Bad Request |
| PUT    | `/update/{id}`                                 | Update instructor by ID                         | 200 OK, 400 Bad Request |
| DELETE | `/delete/{id}`                                 | Delete instructor by ID                         | 200 OK, 400 Bad Request |
| PUT    | `/assign/{instructorId}/{courseId}`            | Assign course to instructor                     | 200 OK, 400 Bad Request |
| GET    | `/get-instructors-by-qualification/{qualification}` | Get instructors by qualification                | 200 OK, 404 Not Found |
| GET    | `/get-courses-by-instructor/{instructorId}`    | Get courses assigned to a specific instructor   | 200 OK, 404 Not Found |
| GET    | `/get-instructors-by-department/{department}`  | Get instructors by department                   | 200 OK, 404 Not Found |

#### Request/Response Examples

**Add Instructor:**
```http
POST /api/v1/instructors/add
Content-Type: application/json

{
  "id": "INS001",
  "name": "Dr. Jane Smith",
  "email": "jane.smith@university.edu",
  "department": "Computer Science",
  "qualification": "PhD"
}
```

**Assign Course to Instructor:**
```http
PUT /api/v1/instructors/assign/INS001/CS101
```

**Response:**
```json
{
  "message": "Course assigned to instructor successfully"
}
```

---

### 📚 Courses

**Base Path:** `/api/v1/courses`

| Method | Endpoint                                  | Description                        | Status Codes           |
|--------|-------------------------------------------|------------------------------------|------------------------|
| GET    | `/get`                                    | Get all courses                    | 200 OK                 |
| POST   | `/add`                                    | Add a new course                   | 200 OK, 400 Bad Request |
| PUT    | `/update/{id}`                            | Update course by ID                | 200 OK, 400 Bad Request |
| DELETE | `/delete/{id}`                            | Delete course by ID                | 200 OK, 400 Bad Request |
| GET    | `/get-by-semester/{semester}`             | Get courses by semester            | 200 OK, 404 Not Found |
| GET    | `/change-availability/{courseId}/{available}` | Change course availability         | 200 OK, 404 Not Found |
| GET    | `/check-availability/{courseId}`          | Check if a course is available     | 200 OK, 404 Not Found |

#### Request/Response Examples

**Add Course:**
```http
POST /api/v1/courses/add
Content-Type: application/json

{
  "id": "CS101",
  "name": "Introduction to Programming",
  "credits": 3,
  "semester": "Fall 2024",
  "available": true
}
```

**Change Course Availability:**
```http
GET /api/v1/courses/change-availability/CS101/false
```

**Check Course Availability:**
```http
GET /api/v1/courses/check-availability/CS101
```

**Response:**
```json
{
  "message": "Course is available"
}
```

---

### 📄 Enrollments

**Base Path:** `/api/v1/enrollments`

| Method | Endpoint                              | Description                                | Status Codes           |
|--------|---------------------------------------|--------------------------------------------|------------------------|
| POST   | `/enroll/{studentId}/{courseId}`      | Enroll student in course                   | 200 OK, 400 Bad Request |
| DELETE | `/drop/{studentId}/{courseId}`        | Drop student from course                   | 200 OK, 400 Bad Request |
| GET    | `/get-all`                            | Get all enrollments                        | 200 OK                 |
| GET    | `/student/{studentId}`                | Get all courses a student is enrolled in   | 200 OK                 |

#### Request/Response Examples

**Enroll Student in Course:**
```http
POST /api/v1/enrollments/enroll/STD001/CS101
```

**Response:**
```json
{
  "message": "Student enrolled successfully in course."
}
```

**Drop Course:**
```http
DELETE /api/v1/enrollments/drop/STD001/CS101
```

**Response:**
```json
{
  "message": "Course dropped successfully."
}
```

**Get Student's Courses:**
```http
GET /api/v1/enrollments/student/STD001
```

**Response:**
```json
[
  {
    "id": "CS101",
    "name": "Introduction to Programming",
    "credits": 3,
    "semester": "Fall 2024",
    "available": true
  }
]
```

---

## 📋 Response Formats

### Success Response
```json
{
  "message": "Operation completed successfully"
}
```

### Validation Error Response
```json
"Field validation error message"
```

### Not Found Response
```json
{
  "message": "Resource not found"
}
```

---

## 🔍 HTTP Status Codes

| Status Code | Description |
|-------------|-------------|
| 200 | OK - Request successful |
| 400 | Bad Request - Invalid input, validation errors, or resource not found |
| 404 | Not Found - Resource not found or no data available |

---

## 🧪 Testing Examples

### 1. Complete Student Workflow
```bash
# Add a student
curl -X POST http://localhost:8080/api/v1/students/add \
  -H "Content-Type: application/json" \
  -d '{"id":"STD001","name":"John Doe","email":"john@example.com","major":"Computer Science","gpa":3.75}'

# Get all students
curl -X GET http://localhost:8080/api/v1/students/get

# Get students by major
curl -X GET http://localhost:8080/api/v1/students/get-by-major/Computer%20Science

# Get highest GPA student
curl -X GET http://localhost:8080/api/v1/students/get-highest-gpa
```

### 2. Complete Course Workflow
```bash
# Add a course
curl -X POST http://localhost:8080/api/v1/courses/add \
  -H "Content-Type: application/json" \
  -d '{"id":"CS101","name":"Programming","credits":3,"semester":"Fall 2024","available":true}'

# Get courses by semester
curl -X GET http://localhost:8080/api/v1/courses/get-by-semester/Fall%202024

# Change course availability
curl -X GET http://localhost:8080/api/v1/courses/change-availability/CS101/false

# Check course availability
curl -X GET http://localhost:8080/api/v1/courses/check-availability/CS101
```

### 3. Complete Instructor Workflow
```bash
# Add an instructor
curl -X POST http://localhost:8080/api/v1/instructors/add \
  -H "Content-Type: application/json" \
  -d '{"id":"INS001","name":"Dr. Smith","email":"smith@uni.edu","department":"CS","qualification":"PhD"}'

# Assign course to instructor
curl -X PUT http://localhost:8080/api/v1/instructors/assign/INS001/CS101

# Get instructors by qualification
curl -X GET http://localhost:8080/api/v1/instructors/get-instructors-by-qualification/PhD

# Get courses by instructor
curl -X GET http://localhost:8080/api/v1/instructors/get-courses-by-instructor/INS001
```

### 4. Complete Enrollment Workflow
```bash
# Enroll student in course
curl -X POST http://localhost:8080/api/v1/enrollments/enroll/STD001/CS101

# Get all enrollments
curl -X GET http://localhost:8080/api/v1/enrollments/get-all

# Get student's courses
curl -X GET http://localhost:8080/api/v1/enrollments/student/STD001

# Drop course
curl -X DELETE http://localhost:8080/api/v1/enrollments/drop/STD001/CS101
```

---

## 🔧 Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Web**
- **Jakarta Validation**
- **Lombok**
- **RESTful API Design**

---

## 🧑‍💻 Author

Built with 💻 by Mohammed Alherz