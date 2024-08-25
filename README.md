# 27four – Offsite Java assignment galaxy travel

## Background 
They requirement is to build a system that will allow them to find the shortest path from point “A” "source" ,
being Earth, through the galaxy to any of the planets represented by the other nodes "destination". 
## Tools required
- Java 17 or later
- Maven 3.5+ 
- IDE of Choice
### To install and run
run
``$ mvn clean install``
then
``mvnw spring-boot:run``

go to http://localhost:8080/
### Usage
 the end user has to import the data from the csv file provided.To import the data go to [swagger ] http://localhost:8080/swagger-ui/index.html and import the data via the POST end points provided.
 at http://localhost:8080/ is a UI to enter a source planet and a destination planet.
