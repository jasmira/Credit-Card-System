# Creditcardclient

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 12.0.0.

## How To Run the client

* Download project to a folder
* Go inside the project folder
* Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
* OR you can also run `ng serve --open` to directly open your browser and navigate to `http://localhost:4200/`

## How to run the tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).


# Creditcardserver

# Credit Card System Server

This is a Java (version 8) / Maven (3.x) / Spring Boot (version 2.3.10.RELEASE) application that serves as the backend server for a Credit Card system UI. It has services that creates a new credit card and gets all saved credit cards in an in-memory H2 database. 

## How to Run

This application exists as a maven java project. You just need to follow the below steps to get the project up and running:

* Clone this repository
* Make sure you are using JDK 1.8 and Maven 3.x and have it pre-installed on your computer
* Import the project as an existing maven project in any of the IDEs. I use IntelliJ IDEA as my IDE
* Let the IDE download and install all the required maven dependencies
* Once maven dependencies are downloaded, clean and build the project inside the IDE
* You can also clean, build and run the tests in the project via command prompt (CMD) by running ```mvn clean install```
* If you just want to run the test cases alone, you can do so by running ```mvn test```
* Once successfully built, you can run the service as Java application inside the IDE itself.

Once the application runs you should see something like this

```
2021-05-18 10:52:04.626  INFO 13600 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-05-18 10:52:04.664  INFO 13600 --- [           main] c.a.c.CreditCardSystemApplication        : Started CreditCardSystemApplication in 10.509 seconds (JVM running for 15.569)
```

Also, you should see the below statements in the console when the application runs. This confirms that your in-memory H2 database is configured properly and running:

```
2021-05-18 10:51:58.100  INFO 13600 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2021-05-18 10:51:58.316  INFO 13600 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 178ms. Found 1 JPA repository interfaces.
.....
.....
2021-05-18 10:52:00.356  INFO 13600 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-05-18 10:52:00.673  INFO 13600 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2021-05-18 10:52:00.757  INFO 13600 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2'......
2021-05-18 10:52:01.173  INFO 13600 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
.....
.....
2021-05-18 10:52:01.821  INFO 13600 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2021-05-18 10:52:02.961  INFO 13600 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2021-05-18 10:52:02.976  INFO 13600 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
.....
```

## About the Service

The service is just a simple credit card maintenance REST service. It allows you to create a new credit card ad save it in the database and also to get all existing saved credit cards from the database. It uses an in-memory database (H2) to store the data. If the database connection properties work, you can call some REST endpoints defined in ```com.assignment.creditcardsystem.controller.CardController;``` on **port 8080**. It also does a few more checks like checking for a valid credit card number based on Luhn 10 algorithm.

Here is what this application demonstrates:

* Full integration with the **Spring** Framework: inversion of control, dependency injection, etc.
* Writing a RESTful service using annotation: supports JSON request / response.
* Contains custom exception and maps this exception to the right HTTP response with exception details in the body.
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and annotations.
* CRUD functionality against the data source using Spring *Repository* pattern (GET and POST APIs)
* Demonstrates MockMVC test framework with associated libraries for testing
* App is dockerised to run inside a container

Here are some endpoints you can call:
### Create a new credit card

```
URL: POST http://localhost:8080/cards
Accept: application/json
Content-Type: application/json

{
"name" : "Bob",
"cardNumber" : 1052118567,
"limits" : 435,
"balance" : 4328.9
}

RESPONSE: HTTP 200 (OK)
Content: Id of the newly created credit card, which is of type long 
```

### Retrieve a list of existing/saved credit cards

```
URL: GET http://localhost:8080/cards
Accept: application/json
Content-Type: application/json

Response: HTTP 200 (OK)
Content: List  of credit cards
```

### To view your H2 in-memory database console

This app runs an inbuilt H2 in-memory database so that you don't have to install a database in order to run it. To view and query the database you can browse to http://localhost:8080/h2. Default username is 'sa' and has a password for which you can refer to the ```application.properties``` file inside the project.

### To run the app inside a docker container

The app is dockerised to run inside a docker container. To do so, follow below steps:

* Make sure docker is installed on your computer beforehand
* Check if docker works inside your command prompt by checking the docker version ```docker version```
* If docker is installed, create the image for the app by running command
```
docker build --tag=credit-card-server:latest .
```
* Once the image is created successfully, run the container from the image created by running command
```
docker run -p8080:8080 credit-card-server:latest
```
* This will successfully run the app inside the container and we can access the REST APIs exposed by this spring boot app