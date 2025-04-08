# Stock Management System 📦

A simple stock management system built with java.

## 🚀 Features
[TODO]

## 🛠️ Concepts Used
[TODO]

## 📂 Project Structure
[TODO]

## 📦 Database Structure
![Database Structure](/db_schema.png)

## ⚡ How to Run
1. Clone the repository:
  ```bash
    git clone https://github.com/AzizJabri/ProjetJava.git
```
1. Open the project in your favorite Java IDE.
2. Add mysql-connector-java dependency to your project:
   - If you are using Maven, add the following to your `pom.xml`:
     ```xml
     <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
         <version>8.0.32</version>
     </dependency>
     ```
   - If you are not using Maven, download the mysql-connector-java jar file and add it to your classpath.
3. Create a MySQL database named `projet_java` and run the SQL script `db.sql`.
4. Update the database connection details in the `DBConnection.java` file:
   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/projet_java";
   private static final String USER = "your_username";
   private static final String PASS = "your_password";
   ```
   Replace `your_username` and `your_password` with your MySQL credentials.
    XAMPP Default username: `root` and password: `""` (empty string).
5. Run **Main.java** to start the program.
      
