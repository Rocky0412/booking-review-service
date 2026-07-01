# Booking Review Service

A RESTful backend application built with Spring Boot for managing ride bookings and customer reviews. The project demonstrates CRUD operations, entity relationships, REST API design, and Spring Data JPA.

---

## Features

- Create a review for a booking
- Edit an existing review
- Delete a review
- Retrieve all reviews
- Retrieve a review by ID
- One-to-One relationship between Booking and Review
- Spring Data JPA Repository
- MySQL Database Integration
- RESTful APIs

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok

---

## Project Structure

```
src
├── controllers
├── models
├── repositories
├── RequestDTO
├── config
└── resources
```

---

## Entity Relationship

```
Booking
--------
id
price
status
review_id  ----------> Review.id

Review
-------
id
content
rating
```

Each booking can have at most one review.

---

## API Endpoints

### Get All Reviews

```
GET /api/v1/reviews
```

---

### Get Review By ID

```
GET /api/v1/reviews/{id}
```

---

### Create Review

```
POST /api/v1/reviews/{booking_id}/create
```

Request Body

```json
{
  "content": "Excellent ride",
  "rating": 4.8
}
```

---

### Edit Review

```
PATCH /api/v1/reviews/edit/{review_id}
```

Request Body

```json
{
  "comment": "Driver was very professional."
}
```

---

### Delete Review

```
DELETE /api/v1/reviews/{review_id}
```

---

## Database

MySQL is used as the relational database.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Running the Project

Clone the repository

```bash
git clone https://github.com/your-username/booking-review-service.git
```

Navigate to the project

```bash
cd booking-review-service
```

Run the application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

## Concepts Demonstrated

- REST API Development
- Spring Boot
- Spring Data JPA
- Hibernate ORM
- DTO Pattern
- Entity Relationships
- One-to-One Mapping
- Cascade Operations
- CRUD Operations
- Repository Pattern
- Exception Handling

---

## Future Improvements

- JWT Authentication
- User Roles (Admin, Customer)
- Validation using Jakarta Validation
- Pagination and Sorting
- Global Exception Handling
- Swagger/OpenAPI Documentation
- Docker Support
- Unit & Integration Testing

---

## License

This project is intended for learning and demonstration purposes.
