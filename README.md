# Authentication service

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/227a02ac09084a59b7b2241ef04ed6cd)](https://app.codacy.com/app/makbn/authentication-service?utm_source=github.com&utm_medium=referral&utm_content=makbn/authentication-service&utm_campaign=badger)

Authentication service for Cloud Computing Project following instruction shows you how to run or use this service:
 1. [Running Serivice as a docker container](#running)
 2. [Using serivce](#using)

## Running

First of all you need to run this service,service is ready and you can just pull image from docker hub and run it and done!but if you want to create your own image (it's not necessary) [along these lines you can build yours](#create-your-own-image).
 
### pulling from docker hub:

```terminal
docker pull makbn/cc-authentication-service 
```
it's done and now you can run it and link to database service:

```terminal
docker run --name auth_service -d -it --link db_service:db_service_host -p 8080:8080 makbn/cc-authentication-microservice:latest -sleep 1000000000
```
this ready to use image works on port `8080` and communicate with db_service on port `3306` and host `db_service_host` means that you need to link this image to your database container! here is database user info for this serivce:

**url**=`jdbc:mysql://db_service_host:3306/cc_project_database`

**username**=`auth_service_user`

**password**=`Auth_service@1397`

user host is `%` : `auth_service_user@'%'`

### create your own image:

1. download `jar` file from [here](https://github.com/makbn/authentication-service/releases/tag/1.0-SNAPSHOT) or you can [build your own jar from source code](#building-jar-from-spring-project)
2. create a new empty directory
3. move downloaded `jar` file to the directory
4. create `Dockerfile`:
  
```dockerfile
FROM java:openjdk-8-jdk-alpine
ADD /CC-Authentication-Micro-Service-1.0-SNAPSHOT.jar //
ENTRYPOINT ["java","-jar","/CC-Authentication-Micro-Service-1.0-SNAPSHOT.jar"]
EXPOSE 8080
```
5. open terminal change direction to the directory of Dockerfile
6. run `docker build` command:

```terminal
docker build -t cc-authentication-service -f /Users/makbn/IdeaProjects/CCAuthenticationMicroService/docker/Dockerfile .
```
7.done.

## Building `jar` from java Spring project:

for spring project with `pom` file it easy to generate a `jar` file just by adding Spring boot maven plugin:

```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

```

after adding this line use `mvn` command:

```terminal
mvn package
```

## Using

### Student login:
* Request : `GET`
* Parameters : `studentNumber`,`password`
* Example:
```
http://localhost:8080/student/login?studentNumber=9312430000&password=123456
```
* Success response ( `200 OK`):
```json
{
   "result": {
       "session": "eyJleHBpcmVUaW1lIjoxNTIyNDkxMTgxMTQ3LCJjcmVhdGVUaW1lIjoxNTIyNDkwNTgxMTQ3LCJ1c2VySWRlbnRpZmllciI6IjkzMTI0MzAwMDAiLCJzY29wZSI6IkFsbCIsInNlc3Npb25TdHJpbmciOiJLd3dMMTU2OTM3Q1BqVXN3dFhOTTk0SyJ9",
       "status": "new"
   },
   "timestamp": "2018-03-31T10:03:01.222+0000",
   "status": 200
}
```
* Error response:

1. `404 not found`:

```json
  {
    "path": "/student/login",
    "error": "username not found",
    "message": "no student exists with entered student number",
    "timestamp": "2018-03-31T12:37:20.227+0000",
    "status": 404
}
```
2. `400 bad request`:
  ```json
  {
    "timestamp": "2018-03-31T12:38:08.069+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Parameter conditions \"studentNumber, password\" not met for actual request parameters: studentNumber={9312430009}, passwor={123456}",
    "path": "/student/login"
}
```

### Authenticate:

* Request : `GET`
* Parameters : `studentNumber`,`session`
* Example:
```
http://localhost:8080/authenticate?studentNumber=9312430000&session=eyJleHBpcmVUaW1lIjoxNTIyNDkxMTgxMTQ3LCJjcmVhdGVUaW1lIjoxNTIyNDkwNTgxMTQ3LCJ1c2VySWRlbnRpZmllciI6IjkzMTI0MzAwMDAiLCJzY29wZSI6IkFsbCIsInNlc3Npb25TdHJpbmciOiJLd3dMMTU2OTM3Q1BqVXN3dFhOTTk0SyJ9
```
* Success response ( `200 OK`):
```json
{
    "result": {
        "message": "user session is valid and refreshed."
    },
    "timestamp": "2018-03-31T10:03:25.781+0000",
    "status": 200
}
```
* Error response (`401 unauthorized`):
```json 
{
    "path": "/authenticate",
    "error": "session expired",
    "message": "user session is not valid!need login to access.",
    "timestamp": "2018-03-31T12:49:25.069+0000",
    "status": 401
}
```
