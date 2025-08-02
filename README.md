# Tienda_m

A web store application built with Spring Boot using a layered architecture, multi-language support, advanced security, and multimedia resources. Features authentication and roles, shopping cart, invoicing, and is ready for Docker deployment.

## 🚀 Tech Stack
- **Backend:** Java 17+, Spring Boot, Spring Data JPA, Spring Security
- **Frontend:** Thymeleaf, Bootstrap, JS
- **Database:** MySQL
- **DevOps:** Docker, Maven, Git
- **Extras:** Firebase (file storage), Internationalization (EN, ES, FR, PT)

## 🏗️ Architecture
[User] → [Controllers] → [Services] → [Repositories] → [Entities] → [Database]


## 📦 Main Modules
- **Users & Security:** Registration, activation, recovery, role-based access control
- **Products & Categories:** CRUD, image uploads via Firebase, filters
- **Cart & Sales:** Cart management, checkout, invoicing, stock control
- **Internationalization:** Multi-language support
- **Multimedia:** Centralized static and media resources

## 🔑 Key Flows
- Registration and email activation
- Shopping with cart and automatic invoice generation
- Full catalog management
- Security based on roles and allowed routes

## 🛠️ Best Practices
- Clear layer separation (MVC)
- DRY Thymeleaf and Bootstrap templates
- Secure configuration management
- Ready for CI/CD and Docker deployment

## ▶️ Quick Start
1. Requirements: Java 17+, Maven, MySQL
2. Configure `application.properties`
3. Build and run:
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```
    or use the Dockerfile
4. Open [http://localhost:8080](http://localhost:8080) in your browser

---
Developed following modern engineering standards.  
Stack: Java, Spring Boot, JPA, Thymeleaf, Bootstrap, Docker, Firebase.
