# Customer Relationship Management System (CRM) 🚀


![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen.svg)
![Maven](https://img.shields.io/badge/Build-Maven-blue.svg)
![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)

> A **production-grade CRM backend** built with **Java 17** and **Spring Boot 3.0**.  
> Designed for enterprise scalability, this RESTful API handles customer management with precision, offering robust CRUD operations, advanced filtering, and analytics.  

---

## 🎯 Features

- **Customer Management**:
  - Create, read, update, and delete customers.
  - Update individual fields: `firstName`, `lastName`, `email`, `mobileNumber`, `age`.
- **Advanced Filtering**:
  - Search by first name, last name, email, or mobile number.
  - Filter by exact age, age less than, greater than, or between a range.
- **Analytics**:
  - Total customer count.
  - Group customers by age for insights.
- **Bulk Operations**:
  - Insert multiple customers with a single POST request.
- **Architecture**:
  - Clean 3-tier design: `Controller → Service → DAO`.

---

## 📐 Entity Relationship Diagram

```mermaid
erDiagram
    CUSTOMER {
        int id PK
        string firstName
        string lastName
        string email
        string mobileNumber
        int age
    }

    LEAD {
        int id PK
        string source
        string status
        string createdDate
    }

    OPPORTUNITY {
        int id PK
        string stage
        double value
        string expectedCloseDate
    }

    SALES_PIPELINE {
        int id PK
        string pipelineName
        string description
    }

    CUSTOMER ||--o{ LEAD : "generates"
    LEAD ||--o{ OPPORTUNITY : "converted_to"
    OPPORTUNITY }o--|| SALES_PIPELINE : "tracked_in"

```
---
## ⚙️ Tech Stack  

| Layer            | Technology |
|------------------|------------|
| **Backend**      | ![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white) |
| **ORM**          | ![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666C?logo=hibernate&logoColor=white) ![JPA](https://img.shields.io/badge/JPA-Specification-blue) |
| **Database**     | ![H2](https://img.shields.io/badge/H2-Database-003545?logo=databricks&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?logo=mysql&logoColor=white) |
| **Build Tool**   | ![Maven](https://img.shields.io/badge/Maven-Build%20Tool-C71A36?logo=apachemaven&logoColor=white) |
| **API Testing**  | ![Postman](https://img.shields.io/badge/Postman-API%20Testing-FF6C37?logo=postman&logoColor=white) |
| **Documentation**| ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger&logoColor=black) |
| **Version Control** | ![Git](https://img.shields.io/badge/Git-F05032?logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/GitHub-100000?logo=github&logoColor=white) |
| **Deployment**   | ![Railway](https://img.shields.io/badge/Railway-Cloud%20Hosting-0B0D0E?logo=railway&logoColor=white) |
| **IDE**          | ![Eclipse](https://img.shields.io/badge/Eclipse-IDE-2C2255?logo=eclipseide&logoColor=white) |

---

## 🧪 API Endpoints

| Method | Endpoint                             | Description                          |
|--------|--------------------------------------|--------------------------------------|
| POST   | `/api/customers/insert`              | Insert a single customer             |
| POST   | `/api/customers/bulk-insert`         | Insert multiple customers            |
| GET    | `/api/customers`                     | Get all customers                   |
| GET    | `/api/customers/{id}`                | Get customer by ID                   |
| PUT    | `/api/customers/update-email/{id}`   | Update email                        |
| PUT    | `/api/customers/update-age/{id}`     | Update age                          |
| GET    | `/api/customers/byage/{age}`         | Get customers by exact age           |
| GET    | `/api/customers/between-age/{min}/{max}` | Get customers in age range       |
| GET    | `/api/customers/grouped-by-age`      | Group customers by age               |
| GET    | `/api/customers/count`               | Total customer count                |

---

## 📊 Sample Data Snapshot  

Below is a quick look at how the data in each entity might look inside the **CRM Sales Pipeline** system.  

### 👤 Customer  
| id | firstName | lastName | email               | mobileNumber | age |
|----|-----------|----------|---------------------|--------------|-----|
| 1  | Tanushree | Sarkar   | tanushree@mail.com  | 9876543210   | 24  |
| 2  | Arjun     | Mehta    | arjun.mehta@mail.com| 9123456789   | 29  |

---

### 🧲 Lead  
| id | title           | status    | createdDate | customerId |
|----|-----------------|-----------|-------------|------------|
| 1  | Website Signup  | New       | 2025-09-01  | 1          |
| 2  | Product Inquiry | Contacted | 2025-09-05  | 2          |

---

### 💼 Deal  
| id | name              | amount | stage        | expectedCloseDate | leadId |
|----|-------------------|--------|--------------|-------------------|--------|
| 1  | CRM Integration   | 5000   | Negotiation  | 2025-09-20        | 1      |
| 2  | Premium Package   | 12000  | Proposal Sent| 2025-10-02        | 2      |

---

### 📊 Pipeline Stage  
| id | stageName    | description                  |
|----|--------------|------------------------------|
| 1  | Prospecting  | Identifying potential leads  |
| 2  | Negotiation  | Discussing terms and pricing |
| 3  | Closed Won   | Deal successfully closed     |
| 4  | Closed Lost  | Deal lost to competition     |

---

## 📂 Project Structure

```
CustomerCRM/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/
│   │   │   │   └── CustomerController.java
│   │   │   ├── service/
│   │   │   │   └── CustomerService.java
│   │   │   ├── dao/
│   │   │   │   └── CustomerDao.java
│   │   │   ├── entity/
│   │   │   │   └── Customer.java
│   │   │   └── CrmApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## 🛠️ Prerequisites

- **Java 17** or higher
- **Maven 3.8+**
- **MySQL** (for production) or **H2** (embedded for development)
- **Eclipse IDE** (or any preferred IDE)
- **Git** (for version control)
- **Postman** (for API testing)

---

## 🚀 Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/TanushreeSarkar/crm-system.git
   cd crm-system
   ```

2. **Configure Database**:
   - For **H2** (default):
     - Update `application.properties`:
       ```properties
       spring.datasource.url=jdbc:h2:mem:crmdb
       spring.datasource.driverClassName=org.h2.Driver
       spring.datasource.username=sa
       spring.datasource.password=
       spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
       ```
   - For **MySQL**:
     - Install MySQL and create a database named `crm`.
     - Update `application.properties`:
       ```properties
       spring.datasource.url=jdbc:mysql://localhost:3306/crm
       spring.datasource.username=root
       spring.datasource.password=your_password
       spring.jpa.hibernate.ddl-auto=update
       ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   The app will run on `http://localhost:8080`.

5. **Test APIs**:
   - Import the [Postman collection](#) (TBD) or use `curl`:
     ```bash
     curl -X GET http://localhost:8080/api/customers
     ```

6. **Access Swagger UI** (if enabled):
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 🧪 Testing the APIs

Use Postman or curl to test endpoints. Example requests:

- **Insert a Customer**:
  ```bash
  curl -X POST http://localhost:8080/api/customers/insert \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john.doe@example.com","mobileNumber":"1234567890","age":25}'
  ```

- **Get Customers by Age**:
  ```bash
  curl -X GET http://localhost:8080/api/customers/byage/25
  ```

---

## 📚 API Documentation

- Enable Swagger in `pom.xml`:
  ```xml
  <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.0.2</version>
  </dependency>
  ```
- Access Swagger UI at `http://localhost:8080/swagger-ui/index.html`.

---

## 🤝 Contributing

Contributions are welcome! Follow these steps:
1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/YourFeature`.
3. Commit changes: `git commit -m 'Add YourFeature'`.
4. Push to the branch: `git push origin feature/YourFeature`.
5. Open a Pull Request.

---

## 📬 Contact

- **Email**: [2k22.csaiml.2212256@gmail.com](mailto:2k22.csaiml.2212256@gmail.com)
- **GitHub**: [TanushreeSarkar](https://github.com/TanushreeSarkar)

---

## 🙌 Acknowledgments

- Built with ❤️ using [Spring Boot](https://spring.io/projects/spring-boot).
- Inspired by modern CRM platforms like Salesforce and HubSpot.
- Thanks to the open-source community for tools and libraries.

<div align="center">
  <img src="https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExM200aGd3bnpiYWg4NDVldTRiM204em1lOG50cXFncWRvcGhqbGE1bSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/OLPQ6z2hlHmwFc4Hso/giphy.gif" alt="Thank You GIF" width="300"/>
</div>

---

*Star the repo if you find this project useful! 🌟*
