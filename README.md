# Angular 9, Spring boot, JSoup and MySQL Web Applicaton
##  Events Tracker using webscrap
A simple events tracking application built with Angular 9 and Spring boot that connects to the MySQL database.  
Application built using spring data JPA to perform database operations. Users can add, list, update, delete, sort, and filter the expenses.  

# Web Scrapping
Users can add wepages to be scrapped and get events and store in database  
Wepages can be entered in config.xml persent in classpath of spring  

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 8.x.x

4. Angular - 9.x.x

5. Node - 12.x.x

6. Npm - 6.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/maazmmd/EventsTracker-Webscrap.git
```
The Repo is packaged into front-end and back-end  
Front-end: event-tracker-front-end  
Back-end: eventtracker  

**2. Create Mysql database**
```bash
create database eventstracker
```

Once you package and run the back-end DDL script should create tables for you if not find the script below and run manually  
The same can be found in persent in classpath of spring    
```bash
create table tbl_events (id bigint not null auto_increment, end_date varchar(255), event varchar(255), location varchar(255), origin varchar(255), start_date varchar(255), website varchar(255), primary key (id));
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation


**4. Build and run the app using maven (eventsetracker)**
```bash
mvn package
```
You can run the app without packaging it using - (Note below command is to run using command line)

```bash
mvn spring-boot:run
```

The spring boot will start running at <http://localhost:8080>.  
For Production deploy war file generated in target/ folder

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/v1/events
    
    POST /api/v1/events
    
    GET /api/v1/events/{eventId}
    
    DELETE /api/v1/events/{eventId}
    
    WEBSCRAP /api/vi/webscrap

You can test them using postman or any other rest client.

**5. Install node modules for angular application (expense-tracker-frontend)**

```bash
npm install
```

**6. Run the angular application**

You can run the below command to open the application in default web browser -

```bash
ng serve --open
```

The app will start running at <http://localhost:4200>.  
For Production point the rest API URLs as per server configuration in service (ExpenseService) typescript file 

