# Basic Spring Boot application
This application uses the following to create a few 
ReST services:
* Spring Boot
* Jetty
* JDBC (for comparision with JPA with Spring Repository)
* JPA / Hibernate
* Spring Repository
* H2 in-memory database

Of special interest might be the following:
* Handling bidirectional, recursive Employee entity, especially JSON serialization

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

### Employees by last name ReST (using DAO and JdbcTemplate)
Searches employee table by last name, using SQL lastName LIKE '<lastName>%' query
search, with Spring JdbcTemplate.
```
http://localhost:8080/employees?lastNameBeginsWith=J
[{"employeeNumber":1165,"lastName":"Jennings","firstName":"Leslie","extension":"x3291","emailAddress":"ljennings@classicmodelcars.com","officeCode":"1","reportsTo":1143,"jobTitle":"Sales Rep"},{"employeeNumber":1504,"lastName":"Jones","firstName":"Barry","extension":"x102","emailAddress":"bjones@classicmodelcars.com","officeCode":"7","reportsTo":1102,"jobTitle":"Sales Rep"}]
```

### Employees by last name ReST (using Spring Repository)
Searches employee table by last name, using Spring Repository query. Note that the query is ordered,
and the previous one was not.
```
http://localhost:8080/employees/bylastname?beginsWith=J
[{"employeeNumber":1504,"lastName":"Jones","firstName":"Barry","extension":"x102","email":"bjones@classicmodelcars.com","jobTitle":"Sales Rep","office":"7","reportsToEmployee":1102,"reportees":[]},{"employeeNumber":1165,"lastName":"Jennings","firstName":"Leslie","extension":"x3291","email":"ljennings@classicmodelcars.com","jobTitle":"Sales Rep","office":"1","reportsToEmployee":1143,"reportees":[]}]
```

### Employees by last name ReST (using Spring Repository)
Searches employee table by first name, using Spring Repository query.
```
http://localhost:8080/employees/byfirstname?beginsWith=P
[{"employeeNumber":1612,"lastName":"Marsh","firstName":"Peter","extension":"x102","email":"pmarsh@classicmodelcars.com","jobTitle":"Sales Rep","office":"6","reportsToEmployee":1088,"reportees":[]},{"employeeNumber":1401,"lastName":"Castillo","firstName":"Pamela","extension":"x2759","email":"pcastillo@classicmodelcars.com","jobTitle":"Sales Rep","office":"4","reportsToEmployee":1102,"reportees":[]}]
```

### Employees by last name and first name ReST (using Spring Repository)
Searches employee table by last name and first name, using Spring Repository query.
```
http://localhost:8080/employees/bylast-and-firstname?lastNameBeginsWith=J&firstNameBeginsWith=Ba
[{"employeeNumber":1504,"lastName":"Jones","firstName":"Barry","extension":"x102","email":"bjones@classicmodelcars.com","jobTitle":"Sales Rep","office":"7","reportsToEmployee":1102,"reportees":[]}]
```

### Employees by name - can use last name and/or first name
Combines the previous three searches using Spring Repository queries
```
http://localhost:8080/employees/byname?firstName=Ant
[{"employeeNumber":1143,"lastName":"Bow","firstName":"Anthony","extension":"x5428","email":"abow@classicmodelcars.com","jobTitle":"Sales Manager (NA)","office":"1","reportsToEmployee":1056,"reportees":[1165,1166,1188,1216,1286,1323]}]

### Offices by code
Search office by code
```
http://localhost:8080/offices/bycode?officeCode=1
{"officeCode":"1","city":"San Francisco","phone":"+1 650 219 4782","addressLine1":"100 Market Street","addressLine2":"Suite 300","state":"CA","country":"USA","postalCode":"94080","territory":"NA"}
```
### Offices by country
Search office by country, list in descendig order by city name
```
http://localhost:8080/offices/bycountry?country=USA
[{"officeCode":"2","city":"Boston","phone":"+1 215 837 0825","addressLine1":"1550 Court Place","addressLine2":"Suite 102","state":"MA","country":"USA","postalCode":"02107","territory":"NA"},{"officeCode":"3","city":"NYC","phone":"+1 212 555 3000","addressLine1":"523 East 53rd Street","addressLine2":"apt. 5A","state":"NY","country":"USA","postalCode":"10022","territory":"NA"},{"officeCode":"1","city":"San Francisco","phone":"+1 650 219 4782","addressLine1":"100 Market Street","addressLine2":"Suite 300","state":"CA","country":"USA","postalCode":"94080","territory":"NA"}]
```
