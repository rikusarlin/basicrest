# Basic Spring Boot application
This application uses the following to create a few 
ReST services:
* Spring Boot
* Jetty
* JDBC (for comparision with JPA with Spring Repository)
* JPA / Hibernate
* Spring Repository
* H2 in-memory database

## Initialization
Not needed. Just compile and run the jar. Database is created on the fly, with some data.

The database schema is the "classicmodels" sample database from mysqltutorial:
http://www.mysqltutorial.org/mysql-sample-database.aspx

## Features
### Greeting ReST 
A very basic Rest Service to test that things are set up properly.
You can give "name" parameter, and get a JSON reply.
```
http://localhost:8080/greeting?name=Riku 
{"id":1,"content":"Hello, Riku!"}
```

### Employees by last name ReST (Using DAO and JdbcTemplate)
Searches employee table by last name, using SQL lastName LIKE '<lastName>%' query
search, with Spring JdbcTemplate.
```
http://localhost:8080/employees?lastNameBeginsWith=J
[{"employeeNumber":1165,"lastName":"Jennings","firstName":"Leslie","extension":"x3291","emailAddress":"ljennings@classicmodelcars.com","officeCode":"1","reportsTo":1143,"jobTitle":"Sales Rep"},
{"employeeNumber":1504,"lastName":"Jones","firstName":"Barry","extension":"x102","emailAddress":"bjones@classicmodelcars.com","officeCode":"7","reportsTo":1102,"jobTitle":"Sales Rep"}]
```

### Employees by last name ReST (Using Spring Repository)
Searches employee table by last name, using Spring Repository query. Note that the query is ordered,
and the previous one was not.
```
http://localhost:8080/employees/bylastname?beginsWith=J
[{"employeeNumber":1504,"lastName":"Jones","firstName":"Barry","extension":"x102","emailAddress":"bjones@classicmodelcars.com","officeCode":"7","reportsTo":1102,"jobTitle":"Sales Rep"},
{"employeeNumber":1165,"lastName":"Jennings","firstName":"Leslie","extension":"x3291","emailAddress":"ljennings@classicmodelcars.com","officeCode":"1","reportsTo":1143,"jobTitle":"Sales Rep"}]
```

### Employees by last name ReST (Using Spring Repository)
Searches employee table by first name, using Spring Repository query.
```
http://localhost:8080/employees/byfirstname?beginsWith=P
[{"employeeNumber":1612,"lastName":"Marsh","firstName":"Peter","extension":"x102","email":"pmarsh@classicmodelcars.com","jobTitle":"Sales Rep"},{"employeeNumber":1401,"lastName":"Castillo","firstName":"Pamela","extension":"x2759","email":"pcastillo@classicmodelcars.com","jobTitle":"Sales Rep"}]
```

### Employees by last name and first name ReST (Using Spring Repository)
Searches employee table by last name and first name, using Spring Repository query.
```
http://localhost:8080/employees/bylast-and-firstname?lastNameBeginsWith=J&firstNameBeginsWith=Ba
[{"employeeNumber":1504,"lastName":"Jones","firstName":"Barry","extension":"x102","email":"bjones@classicmodelcars.com","jobTitle":"Sales Rep"}]
```

### Employees by name - can use last name and/or first name
Combines the previous three searches using Spring Repository queries
```
http://localhost:8080/employees/byname?lastName=J&firstName=Ba
[{"employeeNumber":1504,"lastName":"Jones","firstName":"Barry","extension":"x102","email":"bjones@classicmodelcars.com","jobTitle":"Sales Rep"}]
```
