<div align="center">

# Academic Management API
**A Spring Boot backend for students, courses and assessments**

Java 17 · Spring Boot · Spring Data JPA · MySQL · Spring Security · JWT

[Getting started](#getting-started) · [API examples](#api-examples) · [Code structure](#code-structure)

</div>

---

## Overview

An academic management backend organized around students, teachers, academic tracks, modules, course elements, evaluations and grades.

The project brings together REST controllers, business services, persistence repositories, DTOs and authentication components. It also includes mail services and data structures for dashboard statistics.

## Functional areas

| Area | Source evidence |
| --- | --- |
| Student management | Student creation, listing, update, deletion and track assignment |
| Academic structure | Modules, tracks and course elements |
| Assessment | Evaluations and grades |
| User accounts | Account entities, user services and verification tokens |
| Authentication | Spring Security configuration and JWT filter/utilities |
| Reporting | Student counts and dashboard-oriented DTOs |

## Getting started

Prerequisites: JDK 17, MySQL, Git and connectivity to download Maven dependencies. SMTP configuration is required for email-related features.

```bash
git clone https://github.com/Salaheddine-Adaoui/gestion-back-remote.git
cd gestion-back-remote
```

Review `src/main/resources/application.properties` and configure your own local database and mail service. Keep real credentials outside version control. Spring configuration can be supplied through environment variables, including:

| Variable | Purpose |
| --- | --- |
| `SPRING_DATASOURCE_URL` | JDBC URL of your local MySQL database |
| `SPRING_DATASOURCE_USERNAME` | Database user |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `SPRING_MAIL_HOST` | SMTP server |
| `SPRING_MAIL_PORT` | SMTP port |
| `SPRING_MAIL_USERNAME` | SMTP username |
| `SPRING_MAIL_PASSWORD` | SMTP password |
| `SERVER_PORT` | Optional HTTP port override |

Create the database before launching. Review the configured Hibernate schema-generation behavior before connecting to any existing database.

```bash
sh mvnw spring-boot:run
```

On Windows, use `mvnw.cmd spring-boot:run`.

## API examples

These routes are defined in [etudiantController.java](src/main/java/com/example/gestion_back/Controller/etudiantController.java). Authentication and authorization depend on the security configuration.

| Method | Route | Purpose |
| --- | --- | --- |
| GET | `/etudiant/allEtudiants` | List students |
| GET | `/etudiant/cin/{cin}` | Find a student |
| POST | `/etudiant/addEtudiant` | Create a student |
| PUT | `/etudiant/updateEtudiant/{cin}` | Update a student |
| GET | `/etudiant/nbretudiant` | Count students |
| GET | `/etudiant/etudiantPerYear` | Read yearly student statistics |

Request payloads are defined by the relevant DTOs and entities. These examples document the source; they are not a claim of live endpoint validation.

## Code structure

All application packages are under `src/main/java/com/example/gestion_back/`.

| Package | Responsibility |
| --- | --- |
| `Controller` | HTTP routes |
| `Services` | Business operations |
| `Repository` | Data access |
| `Entities` | Persistence model |
| `Dto` | Request and response structures |
| `Securite` | Authentication and security configuration |

## Build and validation

```bash
sh mvnw test
sh mvnw package
```

Tests that load the application context may require working database configuration.

## Project status

Academic application. The repository contains the backend implementation; this README does not claim production hardening or a deployed demo. Priorities for further work include an OpenAPI contract, isolated integration tests, configuration cleanup and documented sample requests.

*Documentation checked against the source and Maven configuration. No database-backed execution was performed during this update.*
