### WebServices Course
Assignment 2 (Design and implement order management Backend API)

### 1- How the application was built ?
First, I started to analyze the entities and relations between the resources.
The next step is to build the entities with the required relationships.
After that, the repositories were linked with the JPA.

Then I moved to implement the services for the resources and their required business logic.
On the other hand, we need to put authorization and authentication into consideration.
So, we used the JWT token to achieve that.
The implementation started with making user and role entities, and we made a many-to-many between them.
After that, we create the needed classes to achieve this approach, starting with the security config class.
JwtAuthenticationEntryPoint, JwtAuthenticationFilter, and finally JwtTokenProvider are all used.
which provides us with the token key.

Finally, the deployment was achieved by packaging the project into a jar file, then writing a docker file which contains the instructions to build an image for my application.
and this image was pulled from the docker hub.

This was a very challenging assignment since it gave me new skills:

1-How to build complex entity relations

2-How to implement JWT

3-How to use Docker.
### 2- API Design

For the resources that need authentication, The token key was appended with header as follow :
"Authorization: Bearer TOKEN_VALUE"
For example

"Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYXplZWhfckB5YW
hvby5jb20iLCJpYXQiOjE2NTQ0NTY4MTgsImV4cCI6MTY1NTA2MTYxOH0.geM5VZ8nKZdeeMd_Cxn1W0TehUQwxfW3tM3UMbobaqmzPwJ_v39Ee-Lckv6a5X9aHrNAw5tB6NajkpJ4QRCJpQ"

a. Authentication API(This resource do not need to make authentication)
![img.png](extra/design/Auth_API_Design.png)
b. Customer API(This resource need to make authentication)
![img.png](extra/design/Customer_API_Design.png)
c. Product API(This resource need to make authentication)
![img.png](extra/design/Prodcut_API_Design.png)
c. Stock API(This resource need to make authentication)
![img.png](extra/design/Stock_API_Design.png)
d. Order API(This resource need to make authentication)
![img.png](extra/design/Order_API_Design.png)

### 3-Testing
The system were tested using Postman and the postman collections json files are exits at extra/postman

### 4-Deployment
1-The project were packaged into jar file using this command: 
```bash
.\mvnw clean package -DskipTests
```
2- The Dockerfile was constructed as shown in project

3-The docker image was built using the command below: 
```bash
docker build -t rest-api .
```
4- Run docker image and test it with the existing database.
```bash
docker run --name rest-api -p 127.0.0.1:8080:8080 rest-api
```
5- Make a standalone app(including the database server ) using docker-compose to start the app and MySQL DB together
```bash
docker-compose up
docker-compose down  # if the service already up
```
6-Finally the docker image were pulled to the docker hub and this the link:

### 5- Documentation
Swagger Documentation were used at this project and can be accessed using this link: 
http://localhost:9091/swagger-ui/#/