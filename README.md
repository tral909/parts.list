# Parts.list
test task for JR internship

**Parts.list** is a simple web app with RESTful api, where implemented crud operations, search, filtering and pagination.<br>
Using technologies: Java8, Spring MVC, Hibernate, HTML, CSS, Bootstrap and AngularJS.<br>
Build tool: Maven.


Tested on application Server Tomcat 9 and MySQL 5.7

![image](https://user-images.githubusercontent.com/24511153/48093292-05dcfe00-e220-11e8-89ef-4ba3391a6687.png)

### build
to build app:
```
$ mvn clean package
```
### run with docker
need to be installed: docker, docker-compose

start application:
```
$ docker-compose up -d
```
stop and delete all artifacts
```
$ docker-compose down --volimes
```
open browser for **http://localhost:8888/parts.list/**
### run on localhost
need to be installed: Tomcat 9 and MySQL 5.7
1. change mysql host in ./src/main/webapp/WEB-INF/spring/spring-config.xml:20
from 'db' to 'localhost'
2. build app with maven
3. move ./target/parts.list.war to webapps tomcat's directory
4. start mysql
5. start tomcat
6. open browser for **http://localhost:8080/parts.list/**

you will see picture above :)