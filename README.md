## The Recipe Tree - Recipe Tracker

<p align="center">
  <img src="https://github.com/fkaucher1288/FinalProject/blob/main/TheRecipeTree.png" />
</p>

## Angular and RESTful API Software Application

### Week 16 Final Project at Skill Distillery - February 2, 2022

Built by:

-   [Ben Allegrezza](https://github.com/bengrezza)
-   [Dane Somdahl](https://github.com/thedaneeffect)
-   [Jordan Talbot](https://github.com/jordantlbt)
-   [Jordon Paynter](https://github.com/Panthro22)
-   [Rick Kaucher](https://github.com/fkaucher1288)

This was a one-week-long sprint use Agile Methodologies to exceed the minimum viable product for our final assignment.

### Overview:

This program is designed to be a full-stack RESTful software application that creates, reads, updates and deletes user and recipe data from a database we built using MySQL Workbench and connecting with using Java Database Connectivity.

### How to run:

First, the user enters the index page. The user can choose to:

- Register as a user
- Login if already a user
- Search recipes (logged in or not logged in)

Demo Account:

- Username: marthaStewart
- Password: marthaStewart

A logged in user can:

- Update their profile
- Deactivate their profile
- Add or remove recipes to their favorites list
- Rate and make a review comment about a recipe
- Remove the recipe review that they have made
- Add a new recipe
- Update any recipe
- Deactivate a recipe they have created

The admin user can:

- Remove a review even if they did not create the review
- Remove any recipe even if they did not create the recipe

Admin Account:

- Username: wolfgangPuck
- Password: wolfgangPuck

### Database Schema Design:

![Image of MySQL Database Schema](https://github.com/fkaucher1288/FinalProject/blob/main/DB/recipedb.png)

### Technologies used:

Java 1.8 / EE, Java Persistence API (JPA), REST API, Spring Boot, Spring Security, JUnit, Gradle, Hibernate, JSON, TypeScript, Angular, Visual Studio Code, HTML5, CSS, Bootstrap, XML, MySQL Workbench, Postman, Apache Tomcat, Amazon Web Services (AWS), Atom, Git, GitHub, Trello, and Slack.

### Topics implemented:

-   Build a front-end with TypeScript, Angular, and VS Code.
-   Send asynchronous requests to Java controllers with HTTP to perform CRUD RESTfully.
-   Consume and parse JSON responses with Angular.
-   Dynamic Web Applications using Spring Boot.
-   Tomcat 8 on AWS EC2 Instance.
-   CCS, Material, and Bootstrap to make it web and mobile-friendly.
-   Object-Relational Mapping (ORM).
-   Relational Database: large multi-table SQL Database creation using MySQL Workbench.
-   Java Libraries: SQL, List, and handling exceptions.
-   Object-Oriented Programming in Java: Abstraction, Polymorphism, Inheritance, and Encapsulation.
-   Test-Driven Development using JUNIT Juniper.

### Lessons learned:

This project helped us better grasp the capabilities of Dynamic Web Applications by allowing us to better learn how to:

-   Build a multi-table database using MySQL Workbench.
-   Be an Agile and Scrum development team
-   Use branching on Git/GitHub
-   Understand Spring Security.
-   Use the Angular to generate HTML.
-   Build a Dynamic Web Applications using Spring Boot RESTful API endpoints.
-   CCS, Material, and Bootstrap to make it web and mobile-friendly.

### Public REST API Routes

| Return Type  | Route           | Functionality      |
| ------------ | --------------- | ------------------ |
| `List<Recipe>` | `GET api/recipes` | Get all recipe items |

### Secure REST API Routes

| Return Type                | Route                                                                               | Functionality                        |
| -------------------------- | ----------------------------------------------------------------------------------- | ------------------------------------ |
| `User`                     | `POST register`                                                                     | Register as a new user               |
| `User`                     | `GET authenticate`                                                                  | Authenticate a user                  |
| `User`                     | `PUT api/users/{id}`                                                                | Update a user by id                  |
| `User`                     | `PUT api/users/admin`                                                               | Update a user by enabled or disabled |
| `User`                     | `GET api/users/{id}`                                                                | Get a user by id                     |
| `User`                     | `GET api/users/{username}`                                                          | Get a user by username               |
| `List<User>`               | `GET api/users`                                                                     | Get all users                        |
| `List<Recipe>`               | `GET api/recipes/users`                                                               | Get list of a user's recipes            |
| `Recipe`                     | `DELETE api/recipes/{recipeId}`                                                         | Deactivate recipe by id                |
| `Recipe`                     | `POST api/recipes/users`                                                              | Create recipe item                     |
| `Recipe`                     | `PUT api/recipes/users/{recipeId}`                                                      | Update recipe item by id               |
| `AverageRecipeRating`       | `GET api/users/{userId}/rating`                                        | Get average recipe ratings by user id      |
| `List<Category>`           | `GET api/categories`                                                                | Get all categories                   |
| `List<FavoriteRecipes>` | `GET api/users{userId}/favorites`                             | Get favorite recipes by username             |
| `FavoriteRecipes`       | `POST api/users{userId}/favorites`            | Favorite a Recipe by username                  |
| `FavoriteRecipes`       | `PUT api/users{userId}/favorites`  | Update a favorite recipes by user id                       |
