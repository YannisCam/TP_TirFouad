# TP Spring Boot – TP - Yannis Camelin - Mathias Rakotomavo
## 1 - Description

API REST développée avec Spring Boot pour gérer une bibliothèque avec Auteurs et Livres.
Le projet implémente une architecture MVC complète avec :

- Endpoints CRUD pour auteurs et livres
- Endpoints statistiques
- Gestion centralisée des erreurs via GlobalExceptionHandler

---

## 2 - Architecture du projet

~~~
TP_Bibliotheque/
├── controller/       # Endpoints REST
│   ├── AuthorController.java
│   ├── BookController.java
│   └── StatsController.java
├── service/          # Logique métier
│   ├── AuthorService.java
│   ├── BookService.java
├── repository/       # Accès aux données (Spring Data JPA)
│   ├── AuthorRepository.java
│   └── BookRepository.java
├── domain/           # Entités JPA
│   ├── Author.java
│   ├── Book.java
│   └── Category.java (enum)
├── dto/              # Data Transfer Objects
│   └── BookDTO.java
└── exception/        # Gestion des erreurs
    ├── GlobalExceptionHandler.java
    ├── ResourceNotFoundException.java
    ├── DuplicateResourceException.java
    └── ErrorResponse.java
~~~

---

## 3 - Entités principales

Author
- id : Long (auto-généré)
- firstName : String
- lastName : String
- birthYear : Integer

Book
- id : Long (auto-généré)
- title : String (obligatoire)
- isbn : String (unique, obligatoire)
- year : Integer (obligatoire)
- category : Enum (NOVEL, ESSAY, POETRY, OTHER)(obligatoire)
- author : Author (relation ManyToOne)(obligatoire)

---

## 4 - Prérequis

- Java 25
- Maven 4.0.0
- MySQL

---

## 5 - Installation et lancement

### a - Cloner le dépôt
~~~~
git clone https://github.com/YannisCam/TP_TirFouad
cd TP
~~~~

### b - Configurer la base de données

Créer la base MySQL :
~~~~
CREATE DATABASE bdd;
~~~~

Modifier src/main/resources/application.properties si nécessaire :
~~~~
spring.datasource.url=jdbc:mysql://localhost:3306/bdd
spring.datasource.username=root
spring.datasource.password=
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
~~~~
### c - Compiler et lancer

Avec Maven :
~~~~
mvn clean install
mvn spring-boot:run
~~~~

Ou avec le wrapper Maven :
~~~~
./mvnw spring-boot:run
~~~~

L’API sera accessible à : http://localhost:8080

---

## 6 - Endpoints

### Auteurs (/authors)
- GET	/authors	Liste tous les auteurs
- GET	/authors/{id}	Récupère un auteur par ID
- POST	/authors	Crée un nouvel auteur
- PUT	/authors/{id}	Modifie un auteur
- DELETE	/authors/{id}	Supprime un auteur

### Livres (/books)
- GET	/books	Liste tous les livres
- GET	/books/{id}	Récupère un livre par ID
- POST	/books	Crée un nouveau livre
- PUT	/books/{id}	Modifie un livre
- DELETE	/books/{id}	Supprime un livre

### Statistiques (/stats)
- GET	/stats/books-per-category	Nombre de livres par catégorie
- GET	/stats/top-authors?limit=3	Top N auteurs avec le p

## 7 - Catégories de livres

### Category enum :
- NOVEL : Roman
- ESSAY : Essai
- POETRY : Poésie
- OTHER : Autre

## 8 - ⚠️ Gestion des erreurs

- 404 Not Found : Ressource introuvable
- 409 Conflict : ISBN déjà existant
- 400 Bad Request : Données invalides

Exemple :
~~~
{
  "timestamp": "2025-12-12T12:00:00",
  "status": 404,
  "error": "Resource Not Found",
  "message": "L'entité demandée n'existe pas",
  "path": "/authors/99"
}
~~~
---
## 9 - Tester l’API

Avec Postman 

Créer un auteur :
~~~
curl -X POST http://localhost:8080/authors \
-H "Content-Type: application/json" \
-d '{"firstName":"Victor","lastName":"Hugo","birthYear":1802}'
~~~

Créer un livre :
~~~
curl -X POST http://localhost:8080/books \
-H "Content-Type: application/json" \
-d '{"title":"Les Misérables","isbn":"978-2-07-036012-8","year":1862,"category":"NOVEL","author":["firstName:"victor", "lastName:"Hugo"]}'
~~~

Définir la variable baseUrl = http://localhost:8080
