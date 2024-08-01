# Spring Rest API Project

This is a Spring Boot project that demonstrates user authentication and registration using JSON Web Tokens (JWT). The project showcases essential security features such as user login, registration, and secured endpoints.

## Features

- **User Registration**: Users can register with a username and password.
- **User Authentication**: Registered users can log in and receive a JWT for authentication.
- **JWT Authorization**: Secure API endpoints using JWTs to allow access to authenticated users only.
- **Password Encryption**: User passwords are securely stored using BCrypt encryption.
- **Exception Handling**: Robust error handling for authentication and authorization processes.

## Technologies Used

- **Spring Boot**: Simplifies the development of Java applications.
- **Spring Security**: Provides authentication and authorization features.
- **JWT (JSON Web Tokens)**: Used for securely transmitting information between parties as a JSON object.
- **Spring Data JPA**: Simplifies data access with JPA.
- **H2 Database**: In-memory database for quick setup and testing.
- **Lombok**: Reduces boilerplate code for model objects.
- **PostgreSQL**: A powerful, open source object-relational database system.

## Project Structure

- `config`: Contains security and JWT configuration classes.
- `auth`: Contains REST controllers for handling user registration and authentication.
- `demo`: Contains a demo controller which displays message when a user has accessed the server successfully after registering and authentication.
- `user`: Contains objects defining users, user repository and roles.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL (for production use)

### Running the Application

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/spring-security-demo.git
    cd spring-security-demo
    ```

2. Create an `application.yml` file in `src/main/resources` with the following content:

```yaml
jwt:
  secret:
    key: "your-secret-key"
```
As well as settings for JPA and the PostgreSQL database, here is an example
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/jwt-security
    username:
    password:
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop

    show-sql: true
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

3. Build and run the application:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

4. Access the application at `http://localhost:8080`.

### API Endpoints

- **User Registration**: `POST /api/v1/auth/register`
- **User Login**: `POST /api/v1/auth/authenticate`
- **Secured Endpoint**: `GET /api/v1/democontroller` (Requires JWT)


## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT](https://jwt.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [Lombok](https://projectlombok.org/)

