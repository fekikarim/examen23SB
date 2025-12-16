# 📚 Quiz Management System - Spring Boot Application

A comprehensive Spring Boot application for managing online quizzes, candidates, questions, and responses with advanced features like AOP, scheduling, and RESTful APIs.

---

## 🎯 Project Overview

This project implements a simplified online quiz management system featuring:

- **Candidate Management**: Multiple candidates with different levels (JUNIOR, CONFIRME, SENIOR)
- **Quiz Management**: Multiple quizzes with questions and responses
- **Question Management**: Questions with complexity levels (FACILE, DIFFICILE)
- **Relationship Management**: Bidirectional relationships between quizzes and candidates
- **REST API**: Full REST endpoints for CRUD operations
- **Spring AOP**: Aspect-oriented programming for logging method executions
- **Scheduled Tasks**: Automatic detection of the most difficult quiz every 30 seconds
- **Swagger Documentation**: Interactive API documentation

---

## 🏗️ Architecture & Database Schema

### Entities/Associations

```
┌─────────────┐         ┌─────────┐         ┌──────────┐
│  Question   │────1────│  Quiz   │────*────│ Candidat │
│             │         │         │         │          │
│ - idQuestion│         │ - idQuiz│         │- idCand  │
│ - libelleQ  │         │ - titre │         │ - nom    │
│ - complexite│         │ - spec  │         │ - prenom │
└─────────────┘         │ - date  │         │ - nbQuiz │
       │                └─────────┘         │ - niveau │
       │                                    └──────────┘
       1
       │
    @OneToMany
       │
       ▼
┌─────────────┐         ┌──────────┐
│  Reponse    │         │Complexite│
│             │         │          │
│- idReponse  │         │- FACILE  │
│- libelleR   │         │- DIFFICILE
└─────────────┘         └──────────┘

┌──────────┐
│Niveau    │
│          │
│- JUNIOR  │
│- CONFIRME│
│- SENIOR  │
└──────────┘
```

**Key Constraints:**
- IDs are auto-generated with `IDENTITY` strategy
- Enumerations stored as strings in database
- Quiz-Candidat is bidirectional with Candidat as the child (mappedBy)
- One question belongs to exactly one quiz
- One question can have multiple responses

---

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 17+
- **Database**: MySQL 8.0
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **API Documentation**: Springdoc OpenAPI (Swagger)
- **Logging**: SLF4J with Logback
- **AOP**: Spring AOP
- **Scheduling**: Spring Task Scheduler
- **Lombok**: For reducing boilerplate code

---

## 📋 Implemented Services

### 1. Candidate Management (`/gestion-quiz/candidats`)
- **POST /ajouter** - Add a new candidate
- **GET** - List all candidates
- **GET /{id}** - Get candidate by ID
- **GET /recuperer** - Find candidates by speciality and level
- **PUT /{id}** - Update candidate
- **DELETE /{id}** - Delete candidate

### 2. Quiz Management (`/gestion-quiz/quiz`)
- **POST /ajouter** - Add a new quiz
- **POST** - Create quiz (alternative)
- **GET** - List all quizzes
- **GET /{id}** - Get quiz by ID
- **PUT /affecter** - Assign quiz to candidates
- **PUT /{id}** - Update quiz
- **DELETE /{id}** - Delete quiz
- **GET /plus-difficile/recuperer** - Get the hardest quiz

### 3. Question Management (`/gestion-quiz/questions`)
- **POST /ajouter-avec-reponses** - Add question with responses and assign to quiz
- **GET** - List all questions
- **GET /{id}** - Get question by ID
- **PUT /{id}** - Update question
- **DELETE /{id}** - Delete question

### 4. Response Management (`/gestion-quiz/reponses`)
- **POST** - Create response
- **GET** - List all responses
- **GET /{id}** - Get response by ID
- **PUT /{id}** - Update response
- **DELETE /{id}** - Delete response

---

## 🚀 Quick Start

### Prerequisites
- Java JDK 17 or higher
- Maven 3.6+
- MySQL 8.0+

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/fekikarim/examen23SB.git
   cd examen23SB
   ```

2. **Configure database connection**
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/gestion_quiz
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   The application will start on: `http://localhost:8080/gestion-quiz`

5. **Access Swagger UI**
   ```
   http://localhost:8080/gestion-quiz/swagger-ui.html
   ```

---

## 📡 API Examples

### Add a Candidate
```bash
curl -X POST "http://localhost:8080/gestion-quiz/candidats/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Abidi",
    "prenom": "Oussema",
    "nbQuiz": 0,
    "niveau": "JUNIOR"
  }'
```

### Add a Quiz
```bash
curl -X POST "http://localhost:8080/gestion-quiz/quiz/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "titreQuiz": "Spring Boot Fundamentals",
    "specialite": "Developpement",
    "dateQuiz": "2023-05-25"
  }'
```

### Assign Quiz to Candidate
```bash
curl -X PUT "http://localhost:8080/gestion-quiz/quiz/affecter?titreQuiz=Spring&idCandidat=1"
```

### Find Candidates by Level and Speciality
```bash
curl -X GET "http://localhost:8080/gestion-quiz/candidats/recuperer?specialite=Developpement&niveau=CONFIRME"
```

### Add Question with Responses
```bash
curl -X POST "http://localhost:8080/gestion-quiz/questions/ajouter-avec-reponses?idQuiz=1" \
  -H "Content-Type: application/json" \
  -d '{
    "libelleQ": "What is Spring Boot?",
    "complexite": "FACILE",
    "listR": [
      {"libelleR": "A framework"},
      {"libelleR": "A library"},
      {"libelleR": "A tool"}
    ]
  }'
```

---

## 🔍 Advanced Features

### 1. Spring AOP - Method Logging
An aspect automatically logs the start of all service methods beginning with "ajouter":
```
Début méthode « ajouterCandidat »
Début méthode « ajouterQuiz »
Début méthode « ajouterQuestEtRepEtAffecterQuestAQuiz »
```

### 2. Scheduled Task - Hardest Quiz Detection
Every 30 seconds, the system automatically identifies and displays the quiz with the most difficult questions:
```
========================================
Quiz le plus difficile: Spring Boot
Spécialité: Developpement
Nombre de questions difficiles: 5
========================================
```

### 3. Custom Queries
- Find quiz by title
- Find candidates by level and quiz speciality
- Find the quiz with maximum difficult questions

---

## 📊 Project Structure

```
examen23SB/
├── src/main/java/org/example/examen23sb/
│   ├── entities/
│   │   ├── Candidat.java
│   │   ├── Quiz.java
│   │   ├── Question.java
│   │   ├── Reponse.java
│   │   ├── Complexite.java
│   │   └── Niveau.java
│   ├── repositories/
│   │   ├── CandidatRepository.java
│   │   ├── QuizRepository.java
│   │   ├── QuestionRepository.java
│   │   └── ReponseRepository.java
│   ├── services/
│   │   ├── CandidatService.java
│   │   ├── QuizService.java
│   │   ├── QuestionService.java
│   │   └── ReponseService.java
│   ├── services/impl/
│   │   ├── CandidatServiceImpl.java
│   │   ├── QuizServiceImpl.java
│   │   ├── QuestionServiceImpl.java
│   │   └── ReponseServiceImpl.java
│   ├── controllers/
│   │   ├── CandidatController.java
│   │   ├── QuizController.java
│   │   ├── QuestionController.java
│   │   └── ReponseController.java
│   ├── aspects/
│   │   └── AjoutMethodAspect.java
│   ├── config/
│   │   └── SwaggerConfig.java
│   └── Examen23SbApplication.java
├── src/main/resources/
│   ├── application.properties
│   └── static/
├── pom.xml
└── README.md
```

---

## 🧪 Testing

Use **Swagger UI** or **Postman** to test all endpoints:

1. Open Swagger: `http://localhost:8080/gestion-quiz/swagger-ui.html`
2. Or import the API docs: `http://localhost:8080/gestion-quiz/api-docs`

---

## 📝 Key Implementation Details

### Lombok Annotations Used
- `@Getter` / `@Setter` - Auto-generate getters and setters
- `@NoArgsConstructor` - Generate no-args constructor
- `@AllArgsConstructor` - Generate all-args constructor
- `@ToString` - Auto-generate toString() with @ToString.Exclude for circular refs
- `@Builder` - Builder pattern support
- `@Slf4j` - SLF4J logger injection

### JPA/Hibernate Features
- `@ManyToMany` with bidirectional mapping
- `@OneToMany` with mapped relationships
- `@ManyToOne` with back-references
- Cascade operations on responses
- Eager/lazy loading strategies
- Custom JPQL queries with @Query

### Spring Features
- Dependency Injection with constructor injection
- Service layer with interface contracts
- Repository pattern for data access
- RESTful API with Spring MVC
- AOP for cross-cutting concerns
- Scheduled tasks with @Scheduled
- OpenAPI/Swagger integration

---

## ✨ Quality Assurance

- ✅ All methods follow specified signatures
- ✅ Proper error handling and logging
- ✅ Database constraints enforced
- ✅ Circular reference prevention with JSON annotations
- ✅ Transactional consistency
- ✅ RESTful naming conventions
- ✅ Professional code structure

---

## 👤 Contact

For questions, suggestions, or collaboration opportunities, feel free to reach out:

- **Email**: [feki.karim28@gmail.com](mailto:feki.karim28@gmail.com)
- **LinkedIn**: [Karim Feki](https://www.linkedin.com/in/karimfeki)
- **GitHub**: [Karim Feki](https://github.com/fekikarim)

---

## 📄 License

This project is developed for educational purposes as part of the "Architecture des SI II" course at ESPRIT.

---

## 💡 Final Message

> This project demonstrates a complete implementation of a Spring Boot application with enterprise-level features including REST APIs, AOP, scheduling, and complex entity relationships. It showcases best practices in software architecture, clean code principles, and professional development standards.
>
> Building robust applications is not just about writing code—it's about creating solutions that are maintainable, scalable, and delightful to work with. Every feature, every optimization, and every design decision matters.
>
> Keep pushing your boundaries, embrace new technologies, and never stop learning. The journey of mastery is continuous, and your dedication today will define your excellence tomorrow. 🚀

---

**Last Updated**: December 16, 2025  
**Version**: 1.0.0
