# task-to-do

PLEASE USE DESKTOP_RELEASE BRANCH, IN MASTER BRANCH THERE IS application.properties FOR WEB DEPLOYMENT.

CRUD, MVC WEB APPLICATION, WITH EMBEDED H2 DATABASE, STORED IN FILE, DECOY APP. 

DESCRIPTION
  1. U CAN LOG ON 2 ACCOUNTS:
     
   ROLES {STUDENT, TEACHER}
    1.1. STUDENT1
      1.1.1 USERNAME: student1
      1.1.2 PASSWORD student1
      1.1.2 ID: 1
    1.2. STUDENT2
      1.2.1 USERNAME: student2
      1.2.2 PASSWORD student2
      1.1.2 ID: 2
    
  2. AVAIABLE OPTIONS
    2.1. AS A STUDENT U CAN POST YOUR ANSWEAR TO TASK AND DELETE YOUR ANSWEAR LATER
    2.2. AS A TEACHER U CAN RATE YOUR STUDENTS POSTED ANSWEARS, DELETE YOUR RATING AND MANAGE TASKS

LOCAL HOST PORT: 9000 
H2 DATABASE IN BRWOSER: http://localhost:9000/h2-console

DATABASE
USERNAME: sa 
PASSWORD: 

DATABASE AVAIABLE AT: jdbc:h2:file:./src/database/test

TO RUN PROJECT U NEED ATLEAST JAVA VERSION 19 INSTALLED AND MAVEN 3.1

RUN:

OPEN CONSOLE
GO TO PROJECT LOCATION FROM CONSOLE EX. cd C:\projects\task-to-do
IN CONSOLE ENTER: mvn package exec:java -Dexec.args=""

# GALLERY 

### Start page
![main page](https://github.com/KaczyStary/task-to-do/assets/98557409/2d2158d9-3bbd-4aba-bba0-9b4dcbbb6574)

### Student home page and task
![student tasks](https://github.com/KaczyStary/task-to-do/assets/98557409/48580ddb-214e-4184-b384-67e0f9652a5f)

### Student delete answear
![student delete](https://github.com/KaczyStary/task-to-do/assets/98557409/2bc47817-9e46-47a2-ab36-e5318d4299b3)

### Teacher rate answear
![teacher rate answear](https://github.com/KaczyStary/task-to-do/assets/98557409/43f57c20-d644-41ca-9dfe-187d03e38aca)

### Teacher delete rating
![teacher delete rating](https://github.com/KaczyStary/task-to-do/assets/98557409/e775f8e4-b7c1-471b-8081-bd46a8f823d8)

### Teacher edit Task
![teacher edit task](https://github.com/KaczyStary/task-to-do/assets/98557409/12a5ea7d-7213-46d9-b351-06afa66a1b19)

### Login
![login](https://github.com/KaczyStary/task-to-do/assets/98557409/28c04c42-331a-40bd-9d1e-df15d21de8b9)
