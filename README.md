# 🏢 Human Resource Management System (HRMS)

## 📌 Project Overview (Explanation)

This project is a **Human Resource Management System (HRMS)** built using Spring Boot.

👉 In simple terms, this system helps a company manage:

* Employees 👨‍💼
* Departments 🏢
* Leave requests 📝
* Daily attendance ⏱
* Salary calculation 💰

---

## 🎯 Why this project?

In real companies:

* HR teams manage employee data manually or using software
* Employees apply for leave
* Managers approve/reject requests
* Attendance affects salary

👉 This project simulates a **real company system**

---

## 🧠 How the System Works 

### 👤 Step 1: Register User

A user (employee/admin) is created using the register API.

---

### 👨‍💼 Step 2: Employee Management

* Add employee details
* Assign department
* Store salary

---

### 🏢 Step 3: Department Management

* Create departments like IT, HR
* Employees belong to departments

---

### 📝 Step 4: Leave Management

#### Employee:

* Applies for leave
* Status = **PENDING**

#### HR/Admin:

* Approves or rejects leave

👉 Final status:

* APPROVED ✅
* REJECTED ❌

---

### ⏱ Step 5: Attendance Tracking

* Daily attendance is marked
* Status:

    * PRESENT
    * ABSENT

---

### 💰 Step 6: Payroll (Salary Calculation)

Salary is calculated based on attendance:

👉 Example:

* Monthly salary = ₹30,000
* Total days = 30
* Present days = 25

👉 Final salary:

```text
(30000 / 30) × 25 = ₹25,000
```

---

## 🔐 Security (Login System)

* Uses **Basic Authentication**
* Each request requires:

    * Username
    * Password

👉 System checks user from database and allows access based on role:

| Role       | Access                    |
| ---------- | ------------------------- |
| ADMIN      | Full access               |
| HR_MANAGER | Manage employees & leaves |
| EMPLOYEE   | Limited access            |

---

## 🛠 Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* MySQL
* Lombok
* Swagger

---

## ⚙️ How to Run the Project (Step-by-Step)

### 1️⃣ Clone the Project

```bash
git clone https://github.com/tarunyendu-developer/hrms.git
```

---

### 2️⃣ Open in IDE

* Open project in IntelliJ / Eclipse

---

### 3️⃣ Setup Database (MySQL)

Create database:

```sql
CREATE DATABASE hrms;
```

---

### 4️⃣ Configure Database

Open `application.properties` and update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hrms
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

### 5️⃣ Run Application

```bash
mvn spring-boot:run
```

👉 Server starts at:

```text
http://localhost:8080
```

---

### 6️⃣ Open Swagger (Easy Testing UI)

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 How to Test APIs

Use:

* Swagger UI 
* Postman

---

### 🔑 Basic Authentication

In every request:

```text
Username: admin
Password: 123456
```

---

## 📌 Important APIs

### 🔷 Register User

```
POST /api/auth/register
```

---

### 🔷 Employee

```
POST /api/employees
GET /api/employees
```

---

### 🔷 Department

```
POST /api/departments
GET /api/departments
```

---

### 🔷 Leave

```
POST /api/leaves/apply
PUT /api/leaves/{id}/approve
PUT /api/leaves/{id}/reject
```

---

### 🔷 Attendance

```
POST /api/attendance
GET /api/attendance
```

---

### 🔷 Payroll

```
POST /api/payroll/generate
```

---

## 🧠 Key Concepts Implemented

* Layered Architecture
* DTO Pattern
* Exception Handling
* Role-Based Security
* Database Relationships

---

## 🎯 Project Outcome

This project demonstrates:

* Real-world HR system design
* Backend API development
* Secure authentication
* Business logic implementation

---

## 👨‍💻 Author

**Tarun Yendu**
