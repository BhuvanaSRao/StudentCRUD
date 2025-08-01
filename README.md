# StudentCRUD
This Micro Project is a studentCRUD operation
https://github.com/BhuvanaSRao

Download and Installtion: Required for java Project:
Install Visual Studio IDE
Install jdk-17.0.12_windows-x64_bin
Install apache-tomcat-9.0.107
Install mysqlconnector-java-8.0.24
Install javax.servlet-api-4.0.1
Install xampp-windows-x64-8.2.12-0-VS16-installer
Install winrar-x64-712
Install Maven

Add below Environmental Variables -> System Variable -> Path (after Installations.)
Eg : C:\Program Files\apache-maven-3.9.11\bin
C:\Program Files\MySQL\MySQL Server 8.0\bin
C:\Program Files\Java\jdk-17\bin
C:\xampp\tomcat\lib 

Note : Follow the Project folder Paths (refer StudentCRUD repository in github)

Go to Visual Studio IDE :
1. Add index.html -> Add required code for Frontend
2. Add AddStudentServlet.java -> Add required code for Backend
3. Add ViewStudentServlet.java -> Add required code for Backend
4. Add EditStudentServlet.java -> Add required code for Backend
5. Add UpdateStudentServlet.java -> Add required code for Backend
6. Add DeleteStudentServlet.java -> Add required code for Backend
7. Add web.xml -> Every java file created, file mapping should be done in web.xml file (This is important for compilation of java code)
Eg: 
<servlet>
        <servlet-name>AddStudent</servlet-name>
        <servlet-class>AddStudentServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>AddStudent</servlet-name>
        <url-pattern>/AddStudentServlet</url-pattern>
    </servlet-mapping>

8. DBConnection.java -> Add database configuration code. (add db url, username and password etc)

9. Make sure this file exists inside lib folder - javax.servlet-api-4.0.1  (C:\xampp\tomcat\webapps\StudentCRUD\lib)

Create Database and Table:
http://localhost/phpmyadmin/
Navigate to SQL tab -> Write query to create a database and Table
CREATE DATABASE studentdb;  (This creates Database with name studentdb)

USE studentdb; (Use the db)

To create Table with name students:
CREATE TABLE students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100),
    className VARCHAR(20),
    mark1 INT,
    mark2 INT,
    mark3 INT,
);

Just for your refernce : redirection link to db : http://localhost/phpmyadmin/index.php?route=/table/sql&db=studentdb&table=students

Compilation of java code:
Go to this path : C:\xampp\tomcat\webapps\StudentCRUD\WEB-INF\classes
open command prompt here
javac --release 8 -classpath "C:\xampp\tomcat\webapps\StudentCRUD\lib\javax.servlet-api-4.0.1.jar;C:\xampp\tomcat\webapps\StudentCRUD\lib\mysql-connector-java-8.0.24.jar" -d . AddStudentServlet.java DBConnection.java
javac --release 8 -classpath "C:\xampp\tomcat\webapps\StudentCRUD\lib\javax.servlet-api-4.0.1.jar;C:\xampp\tomcat\webapps\StudentCRUD\lib\mysql-connector-java-8.0.24.jar" -d . ViewStudentServlet.java DBConnection.java
javac --release 8 -classpath "C:\xampp\tomcat\webapps\StudentCRUD\lib\javax.servlet-api-4.0.1.jar;C:\xampp\tomcat\webapps\StudentCRUD\lib\mysql-connector-java-8.0.24.jar" -d . EditStudentServlet.java DBConnection.java
javac --release 8 -classpath "C:\xampp\tomcat\webapps\StudentCRUD\lib\javax.servlet-api-4.0.1.jar;C:\xampp\tomcat\webapps\StudentCRUD\lib\mysql-connector-java-8.0.24.jar" -d . UpdateStudentServlet.java DBConnection.java
javac --release 8 -classpath "C:\xampp\tomcat\webapps\StudentCRUD\lib\javax.servlet-api-4.0.1.jar;C:\xampp\tomcat\webapps\StudentCRUD\lib\mysql-connector-java-8.0.24.jar" -d . DeleteStudentServlet.java DBConnection.java

Note: These above command will compile and creates the .class files for its own java file. (If the class file is created, it means that your code is corrrect and fine with no errors)

Execution of application :
Open XAMPP -> Start all 3 (Apache, MySQL, Tomcat)

Now access the URL (OUTPUT) : http://localhost:8080/StudentCRUD/index.HTML (From here you can work on the application)


Details of the Project:(In technical terms)
Note : To connect frontend to Backend :
 <form action="AddStudentServlet" method="post">  this line of code with trigger the backend code i.e "AddStudentServlet" on click of Submit (Add student) button.

Note : To connect Backend to database :
// Connect to DB and prepare insert statement
Connection con = DBConnection.getConnection();

You have a query to insert record to datbase:
String query = "INSERT INTO students (roll_no, name, class, mark1, mark2, mark3) VALUES (?, ?, ?, ?, ?, ?)";
View Record: ResultSet rs = stmt.executeQuery("SELECT * FROM students");            
Update record: String query = "UPDATE students SET name=?, class=?, mark1=?, mark2=?, mark3=? WHERE roll_no=?";
Delete record: String query = "Delete from students WHERE roll_no=?";







