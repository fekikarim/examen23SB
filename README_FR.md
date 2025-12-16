# 📚 Système de Gestion de Quiz - Application Spring Boot

Une application Spring Boot complète pour la gestion de quiz en ligne, de candidats, de questions et de réponses avec des fonctionnalités avancées comme AOP, la planification et les APIs RESTful.

---

## 🎯 Aperçu du Projet

Ce projet implémente un système simplifié de gestion de quiz en ligne avec les fonctionnalités suivantes :

- **Gestion des Candidats** : Plusieurs candidats avec différents niveaux (JUNIOR, CONFIRME, SENIOR)
- **Gestion des Quiz** : Plusieurs quiz avec questions et réponses
- **Gestion des Questions** : Questions avec niveaux de complexité (FACILE, DIFFICILE)
- **Gestion des Relations** : Relations bidirectionnelles entre quiz et candidats
- **API REST** : Endpoints REST complets pour les opérations CRUD
- **Spring AOP** : Programmation orientée aspect pour l'enregistrement des exécutions de méthodes
- **Tâches Planifiées** : Détection automatique du quiz le plus difficile toutes les 30 secondes
- **Documentation Swagger** : Documentation interactive de l'API

---

## 🏗️ Architecture & Schéma de Base de Données

### Entités/Associations

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

**Contraintes Clés :**
- Les identifiants sont auto-générés avec la stratégie `IDENTITY`
- Les énumérations sont stockées sous forme de chaînes de caractères en base de données
- Quiz-Candidat est bidirectionnelle avec Candidat comme enfant (mappedBy)
- Une question appartient à exactement un quiz
- Une question peut avoir plusieurs réponses

---

## 🛠️ Stack Technologique

- **Framework** : Spring Boot 3.x
- **Langage** : Java 17+
- **Base de Données** : MySQL 8.0
- **ORM** : JPA/Hibernate
- **Outil de Construction** : Maven
- **Documentation API** : Springdoc OpenAPI (Swagger)
- **Logging** : SLF4J avec Logback
- **AOP** : Spring AOP
- **Planification** : Spring Task Scheduler
- **Lombok** : Pour réduire le code passe-partout

---

## 📋 Services Implémentés

### 1. Gestion des Candidats (`/gestion-quiz/candidats`)
- **POST /ajouter** - Ajouter un nouveau candidat
- **GET** - Lister tous les candidats
- **GET /{id}** - Obtenir un candidat par ID
- **GET /recuperer** - Trouver des candidats par spécialité et niveau
- **PUT /{id}** - Mettre à jour un candidat
- **DELETE /{id}** - Supprimer un candidat

### 2. Gestion des Quiz (`/gestion-quiz/quiz`)
- **POST /ajouter** - Ajouter un nouveau quiz
- **POST** - Créer un quiz (alternative)
- **GET** - Lister tous les quiz
- **GET /{id}** - Obtenir un quiz par ID
- **PUT /affecter** - Assigner un quiz à des candidats
- **PUT /{id}** - Mettre à jour un quiz
- **DELETE /{id}** - Supprimer un quiz
- **GET /plus-difficile/recuperer** - Obtenir le quiz le plus difficile

### 3. Gestion des Questions (`/gestion-quiz/questions`)
- **POST /ajouter-avec-reponses** - Ajouter une question avec réponses et assigner au quiz
- **GET** - Lister toutes les questions
- **GET /{id}** - Obtenir une question par ID
- **PUT /{id}** - Mettre à jour une question
- **DELETE /{id}** - Supprimer une question

### 4. Gestion des Réponses (`/gestion-quiz/reponses`)
- **POST** - Créer une réponse
- **GET** - Lister toutes les réponses
- **GET /{id}** - Obtenir une réponse par ID
- **PUT /{id}** - Mettre à jour une réponse
- **DELETE /{id}** - Supprimer une réponse

---

## 🚀 Démarrage Rapide

### Prérequis
- Java JDK 17 ou supérieur
- Maven 3.6+
- MySQL 8.0+

### Installation & Configuration

1. **Cloner le référentiel**
   ```bash
   git clone https://github.com/fekikarim/examen23SB.git
   cd examen23SB
   ```

2. **Configurer la connexion à la base de données**
   Modifiez `src/main/resources/application.properties` :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/gestion_quiz
   spring.datasource.username=root
   spring.datasource.password=votre_mot_de_passe
   ```

3. **Construire le projet**
   ```bash
   mvn clean install
   ```

4. **Exécuter l'application**
   ```bash
   mvn spring-boot:run
   ```
   
   L'application démarrera sur : `http://localhost:8080/gestion-quiz`

5. **Accéder à Swagger UI**
   ```
   http://localhost:8080/gestion-quiz/swagger-ui.html
   ```

---

## 📡 Exemples d'API

### Ajouter un Candidat
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

### Ajouter un Quiz
```bash
curl -X POST "http://localhost:8080/gestion-quiz/quiz/ajouter" \
  -H "Content-Type: application/json" \
  -d '{
    "titreQuiz": "Fondamentaux Spring Boot",
    "specialite": "Developpement",
    "dateQuiz": "2023-05-25"
  }'
```

### Assigner un Quiz à un Candidat
```bash
curl -X PUT "http://localhost:8080/gestion-quiz/quiz/affecter?titreQuiz=Spring&idCandidat=1"
```

### Trouver des Candidats par Niveau et Spécialité
```bash
curl -X GET "http://localhost:8080/gestion-quiz/candidats/recuperer?specialite=Developpement&niveau=CONFIRME"
```

### Ajouter une Question avec Réponses
```bash
curl -X POST "http://localhost:8080/gestion-quiz/questions/ajouter-avec-reponses?idQuiz=1" \
  -H "Content-Type: application/json" \
  -d '{
    "libelleQ": "Qu'est-ce que Spring Boot?",
    "complexite": "FACILE",
    "listR": [
      {"libelleR": "Un cadre"},
      {"libelleR": "Une bibliothèque"},
      {"libelleR": "Un outil"}
    ]
  }'
```

---

## 🔍 Fonctionnalités Avancées

### 1. Spring AOP - Enregistrement des Méthodes
Un aspect enregistre automatiquement le début de toutes les méthodes de service commençant par "ajouter" :
```
Début méthode « ajouterCandidat »
Début méthode « ajouterQuiz »
Début méthode « ajouterQuestEtRepEtAffecterQuestAQuiz »
```

### 2. Tâche Planifiée - Détection du Quiz le Plus Difficile
Toutes les 30 secondes, le système identifie et affiche automatiquement le quiz avec le plus de questions difficiles :
```
========================================
Quiz le plus difficile: Spring Boot
Spécialité: Developpement
Nombre de questions difficiles: 5
========================================
```

### 3. Requêtes Personnalisées
- Trouver un quiz par titre
- Trouver des candidats par niveau et spécialité de quiz
- Trouver le quiz avec le maximum de questions difficiles

---

## 📊 Structure du Projet

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
└── README_FR.md
```

---

## 🧪 Tests

Utilisez **Swagger UI** ou **Postman** pour tester tous les endpoints :

1. Ouvrez Swagger : `http://localhost:8080/gestion-quiz/swagger-ui.html`
2. Ou importez les docs API : `http://localhost:8080/gestion-quiz/api-docs`

---

## 📝 Détails Clés de l'Implémentation

### Annotations Lombok Utilisées
- `@Getter` / `@Setter` - Génération automatique des getters et setters
- `@NoArgsConstructor` - Générer un constructeur sans arguments
- `@AllArgsConstructor` - Générer un constructeur avec tous les arguments
- `@ToString` - Génération automatique de toString() avec @ToString.Exclude pour les références circulaires
- `@Builder` - Support du pattern Builder
- `@Slf4j` - Injection de logger SLF4J

### Fonctionnalités JPA/Hibernate
- `@ManyToMany` avec mappage bidirectionnel
- `@OneToMany` avec relations mappées
- `@ManyToOne` avec références inversées
- Opérations en cascade sur les réponses
- Stratégies de chargement eager/lazy
- Requêtes JPQL personnalisées avec @Query

### Fonctionnalités Spring
- Injection de dépendances avec injection par constructeur
- Couche service avec contrats d'interface
- Pattern Repository pour l'accès aux données
- API RESTful avec Spring MVC
- AOP pour les préoccupations transversales
- Tâches planifiées avec @Scheduled
- Intégration OpenAPI/Swagger

---

## ✨ Assurance Qualité

- ✅ Toutes les méthodes suivent les signatures spécifiées
- ✅ Gestion des erreurs et enregistrement appropriés
- ✅ Contraintes de base de données appliquées
- ✅ Prévention des références circulaires avec annotations JSON
- ✅ Cohérence transactionnelle
- ✅ Conventions de nommage RESTful
- ✅ Structure de code professionnelle

---

## 👤 Contact

Pour des questions, des suggestions ou des opportunités de collaboration, n'hésitez pas à nous contacter :

- **Email** : [feki.karim28@gmail.com](mailto:feki.karim28@gmail.com)
- **LinkedIn** : [Karim Feki](https://www.linkedin.com/in/karimfeki)
- **GitHub** : [Karim Feki](https://github.com/fekikarim)

---

## 📄 Licence

Ce projet est développé à des fins éducatives dans le cadre du cours "Architecture des SI II" à ESPRIT.

---

## 💡 Message Final

> Ce projet démontre une implémentation complète d'une application Spring Boot avec des fonctionnalités de niveau entreprise, notamment les APIs REST, AOP, la planification et les relations d'entités complexes. Il met en avant les meilleures pratiques en architecture logicielle, les principes de code propre et les normes de développement professionnel.
>
> Construire des applications robustes, ce n'est pas seulement écrire du code, c'est créer des solutions qui sont maintenables, évolutives et agréables à utiliser. Chaque fonctionnalité, chaque optimisation et chaque décision de conception compte.
>
> Continuez à repousser vos limites, embrassez les nouvelles technologies et ne cessez jamais d'apprendre. Le chemin de la maîtrise est continu, et votre dévouement d'aujourd'hui définira votre excellence de demain. 🚀

---

**Dernière mise à jour** : 16 décembre 2025  
**Version** : 1.0.0
