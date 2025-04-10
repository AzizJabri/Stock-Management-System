# Stock Management System 📦

A simple stock management system built with java.

## 🚀 Features
- Admin authentication
- Category management
- Supplier management
- Customer management
- Product management
- Stock management
- Sales management


## 🛠️ Concepts Used
[TODO]

## 📂 Project Structure
# Models
- Database entities representing the tables in the database.
- Each model class corresponds to a table in the database and contains attributes that represent the columns of the table.
# Repositories
- Classes responsible for interacting with the database.
- Each repository class contains methods for performing CRUD operations on the corresponding model.
# Services
- Classes that contain the business logic of the application.
- Each service class uses the repository classes to perform operations on the database and contains methods for performing specific tasks.
# Controllers
- Classes that handle user input and output.
- Each controller class contains methods for handling user requests and responses.
# Interfaces
- Interfaces that define the methods for the repository and service classes.
- Each interface is implemented by the corresponding class to provide a contract for the methods that need to be implemented.
# Utils
- Utility classes that contain helper methods for the application (for example `PasswordUtils.java`).

## 📦 Database Structure
![Database Structure](/db_schema.png)

## ⚡ How to Run
1. Clone the repository:
  ```bash
    git clone https://github.com/AzizJabri/ProjetJava.git
```
1. Open the project in your favorite Java IDE (Intellij).
2. Add mysql-connector-java dependency to your project:
   - File > Project Structure > Libraries > Add Library > From Maven
   - If you are not using Maven, download the mysql-connector-java jar file then File > Project Structure > Libraries > Add Library > Java and select the downloaded jar file.
3. Create a MySQL database named `projet_java` and run the SQL script `db.sql`.
4. Update the database connection details in the `DBConnection.java` file:
   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/projet_java";
   private static final String USER = "your_username";
   private static final String PASS = "your_password";
   ```
   - Replace `your_username` and `your_password` with your MySQL credentials. 
   - XAMPP Default username: `root` and password: `""` (empty string).
5. Run **Main.java** to start the program.
      
