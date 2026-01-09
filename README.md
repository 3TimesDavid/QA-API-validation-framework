# QA API Validation Framework

## Overview
This project is a REST API validation framework designed to demonstrate how to validate, test, and document an API in a realistic, professional way.

The focus of the project is not the business complexity of the API, but the quality assurance and validation approach:
- clear API contract  
- automated integration tests  
- negative scenarios  
- and full requirements traceability  

It is intended as a portfolio project aligned with **QA Automation / SDET / backend quality roles**.

---

## Architecture
The repository is structured around three main components:

### 1. System Under Test (SUT)
A simple REST API built with Spring Boot that manages `TestRecord` entities.

**Main features:**
- Health check endpoint  
- Create, retrieve and update TestRecords  
- Input validation and proper HTTP status codes  
- In-memory database (H2) for simplicity  

The SUT acts as a realistic target API for validation.

---

### 2. Automated Integration Tests
Integration tests are implemented using:

- JUnit 5  
- Spring Boot Test  
- MockMvc  

The tests:
- start the Spring context  
- validate real HTTP requests and responses  
- cover happy paths, negative cases, and error handling  
- do not rely on mocks for business logic  

**Test categories:**
- Smoke tests (API availability)  
- Happy path tests  
- Negative tests (invalid input, malformed IDs, non-existing resources)  

---

### 3. Documentation and Traceability
The project includes structured QA documentation:
- API Contract (`docs/api-contract.md`)  
- Test Cases (`docs/test-cases.md`)  
- Requirements Traceability Matrix (`docs/traceability-matrix.md`)  

Each functional requirement is explicitly linked to:
- one or more test cases  
- and to the corresponding automated JUnit test method  

This mirrors how validation is handled in regulated or quality-critical environments.

---

## Endpoints Covered
| Method | Endpoint                     | Description                     |
|--------|------------------------------|---------------------------------|
| GET    | `/health`                    | API health check                |
| POST   | `/test-records`              | Create a TestRecord             |
| GET    | `/test-records/{id}`         | Retrieve a TestRecord by id     |
| PATCH  | `/test-records/{id}/status`  | Update TestRecord status        |

---

## Running the Application

### Start the API (manual testing / Postman)

```bash
mvnw spring-boot:run
```

API will be avaliable at:
- http://localhost:8080

Run autoamted tests:
- mvnw clean test

This will:
- start the Spring context
- execute all integration tests
- validate API behavior automatically

---

**Author Notes**

This project was built as part of a professional reorientation towards IT, leveraging prior experience in testing, validation, and standards-based work, and translating it into modern software development practices.