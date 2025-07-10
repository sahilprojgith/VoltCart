# E-Commerce-Backend-Spring-Boot-
Inspired from TELUSKO SPRING 6 course - you can refer React Frontend code from his Github

Here's Backend:
📦 Features
Product CRUD (Create, Read, Update, Delete)

Search products by name, description, or brand

RESTful API design

Integration with MySQL using Spring Data JPA

Project Structure
lua
Copy
Edit
src/
└── main/
    ├── java/
    │   └── com.telusko.SpringEcom/
    │       ├── controller/       --> Handles HTTP requests
    │       ├── model/            --> Entity classes (e.g., Product)
    │       ├── repository/       --> JPA Repository interfaces
    │       └── service/          --> Business logic
    └── resources/
        ├── application.properties


Configure application.properties
properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/ecomdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true



