# Basic Spring Boot application
This application uses the following to create a few 
ReST services:
* Spring Boot
* H2 database (file mode)
* Jetty
* JDBC

## Initialization
You need to load the database schema and some data
into the database first. You just uncomment the following lines
in application.properties:
spring.datasource.schema=db.sql
spring.datasource.data=dbData.sql
spring.datasource.initialize=true

After initial startup you need to comment the lines again 
in order to avoid duplicating data.

If this does not work, you can enter H2 admin UI and do schema
creation and data loading manually:
http://localhost:8080/h2

JDBC URL can be found in application.properties.

We could utilize Liquibase to make this smoother, but we'll save
that for later.

The scheme is the "classicmodels" sample database from mysqltutorial:
http://www.mysqltutorial.org/mysql-sample-database.aspx

## Features
### Greeting ReST 
A very basic Rest Service to test that things are set up properly.
You can give "name" parameter, and get a JSON reply.

http://localhost:8080/greeting?name=Riku 
{"id":1,"content":"Hello, Riku!"}
### Employee ReST
Searches employee table by last name, using SQL LIKE '%name%'
search, with Spring JdbcTemplate.

http://localhost:8080/employees?lastNameBeginsWith=J
[
  {"employeeNumber":1165,"lastName":"Jennings","firstName":"Leslie","extension":"x3291","emailAddress":"ljennings@classicmodelcars.com","officeCode":"1","reportsTo":1143,"jobTitle":"Sales Rep"},
  {"employeeNumber":1504,"lastName":"Jones","firstName":"Barry","extension":"x102","emailAddress":"bjones@classicmodelcars.com","officeCode":"7","reportsTo":1102,"jobTitle":"Sales Rep"}
]