# ScrabbleCalculator

## Assumptions
This assumes that the machine already has Java installed. If not, please download and install. The version used is v11.
## Prerequisites
Make sure the following are installed on the machine:
* Node.js - Go through the official [website](https://nodejs.org/en/download). The version used during this development is v18.17.1.
* Maven - Go through the official [website](https://maven.apache.org/download.cgi). The version used during this development is v3.9.4.

## Run the Application

### Run the BackEnd service
For the password, it can be set anything. The idea is to externalise the sensitive data. 
Open the terminal and enter the command below:
```bash 
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.datasource.password=password -Dspring-boot.run.profiles=local
```

### Run for the FrontEnd service
Open the terminal and enter the command below:

```bash 
ng serve
```
Once successful, please open the [url](http://localhost:4200/) http://localhost:4200/.

## Additional Information
### API Documentation
This was implemented with Swagger. Please open the url to check the specifications if needed.
```bash 
http://localhost:8082/app/swagger-ui/index.html
```
### Database
This was implemented with in-memory database. Please open the url to open the console with the following configuration:
* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:mydb
* User Name: sa
* Password: <This was supplied and set with -Dspring-boot.run.arguments=--spring.datasource.password>


```bash 
http://localhost:8082/app/h2-console/login.jsp
```

## Test
### Backend Service
```bash
mvn test
```

## Debug
This was implemented with AOP.  Below are the samples debug messages:

```bash 
2023-09-14 10:49:45.232 TRACE 11425 --- [nio-8081-exec-1] o.s.web.method.HandlerMethod             : Arguments: [EntryDto(id=null, word=EXCITING, score=18)]
2023-09-14 10:49:45.237 DEBUG 11425 --- [nio-8081-exec-1] c.p.i.s.impl.CalculatorServiceImpl       : Enter : save() with argument[s] : [EntryDto(id=null, word=EXCITING, score=18)]
2023-09-14 10:49:45.243 DEBUG 11425 --- [nio-8081-exec-1] com.palo.it.model.types.Points           : This is the entry char: E
2023-09-14 10:49:45.244 DEBUG 11425 --- [nio-8081-exec-1] com.palo.it.model.types.Points           : This is the entry char: X
2023-09-14 10:49:45.244 DEBUG 11425 --- [nio-8081-exec-1] com.palo.it.model.types.Points           : This is the entry char: C
2023-09-14 10:49:45.244 DEBUG 11425 --- [nio-8081-exec-1] com.palo.it.model.types.Points           : This is the entry char: I
2023-09-14 10:49:45.244 DEBUG 11425 --- [nio-8081-exec-1] com.palo.it.model.types.Points           : This is the entry char: T
2023-09-14 10:49:45.244 DEBUG 11425 --- [nio-8081-exec-1] com.palo.it.model.types.Points           : This is the entry char: I
2023-09-14 10:49:45.244 DEBUG 11425 --- [nio-8081-exec-1] com.palo.it.model.types.Points           : This is the entry char: N
2023-09-14 10:49:45.244 DEBUG 11425 --- [nio-8081-exec-1] com.palo.it.model.types.Points           : This is the entry char: G
Hibernate: call next value for hibernate_sequence
Hibernate: insert into score_entry (created_by, created_date, updated_by, updated_date, score, word, id) values (?, ?, ?, ?, ?, ?, ?)
2023-09-14 10:49:45.284 DEBUG 11425 --- [nio-8081-exec-1] c.p.i.s.impl.CalculatorServiceImpl       : Exit : save() with result = EntryDto(id=1, word=EXCITING, score=18)
```

We can identify the arguments submitted and the response objects if there are.




