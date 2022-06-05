WebServices Course
Assignment 2 (Design and implement order management Backend API)


### API Design




### package the project
```bash
.\mvnw clean package -DskipTests
```
###  To run the project
```bash
java -jar .\target\rest-api-0.0.1-SNAPSHOT.jar
```
#### OR
```bash
.\mvnw spring-boot:run
```
### Build docker image
```bash
docker build -t rest-api .
```
### Run docker image if you have a ready to be used DB
```bash
docker run --name rest-api -p 127.0.0.1:8080:8080 rest-api

OR using docker-compose to start the app and MySQL DB together

docker-compose down  # if the service already up
docker-compose up
```

### Check for running containers
```bash
docker ps -a
```
### Remove container by ID
```bash
docker rm 8717c6955355
```



## License
[MIT](https://choosealicense.com/licenses/mit/)