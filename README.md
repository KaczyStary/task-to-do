# task-to-do

PLEASE USE DESKTOP_RELEASE BRANCH, IN MASTER BRANCH THERE IS application.properties FOR WEB DEPLOYMENT.

CRUD, MVC WEB APPLICATION, WITH EMBEDED H2 DATABASE, STORED IN FILE. DECOY APP. 

DESCRIPTION
  1. U CAN LOG ON 3 ACCOUNTS:
     
   ROLES {STUDENT, TEACHER, ADMIN}
    1.1. STUDENT1
      1.1.1 USERNAME: student1
      1.1.2 PASSWORD student1
      1.1.2 ID: 1
    1.2. STUDENT2
      1.2.1 USERNAME: student2
      1.2.2 PASSWORD student2
      1.1.2 ID: 2
    1.3. TEACHER1
       1.3.1 USERNAME: teacher1
       1.3.2 PASSWORD: teacher1
       1.1.2 ID: 1
    1.4. ADMIN NOT SUPPORTED
    AFTER LOGIN IF NOT LOGGED BY 'log in as a X' X->{'student', 'teacher', 'admin'} CLICK 'log in as a X' X->{ROLE OF USED ACCOUNT} TO REDIRECT TO ASSIGNED TASKS AS A STUDENT, OR VIEW STUDENTS WITH THEIR ASSIGNED TASKS AND DETAILS.
    
  2. PAGE MOVEMENT
    2.1. AS STUDENT THERE ARE CORRECT LINKS AND REDIRECTS
    2.2. AS A TEACHER USE LINKS, THERE ARE NO BUTTONS TO REDIRECT
    2.2.1 TEACHER LINKS: http://localhost:9000/teacher -> VIEW OF ALL LINKED STUDENTS TO TEACHER WITH LINK TO THEIR ASSIGNED TASKS, http://localhost:9000/teacher/{id} id->{STUDENT ID, TO VIEW THEIR TASKS AND DESCRIPTION}

  3. AS A STUDENT U CAN GO TO TASK AND POST TEXT, THEN IN GENERAL TASK VIEW U CAN SEE YOUR POSTED TEXT, U CANT CHANGE YOUR TEXT AFTER SUBMIT.
  4. AFTER SUBMIT ALL TASKS, U CANT DO ANYTHING MORE, EXCEPT VIEWING PAGES, TO RESTORE CHANGE test.mv.db IN task-to-do/src/database/ FROM ORIGINAL PROJECT TO SUBMIT TASKS AGAIN.  

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
