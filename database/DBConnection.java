package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/projet_java";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;


    private DBConnection() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Failed to connect to the database: " + e.getMessage());
        }
    }

    private static final class DBConnectionHolder {
        private static final DBConnection instance = new DBConnection();
    }

    public static DBConnection getInstance() {
        return DBConnectionHolder.instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
