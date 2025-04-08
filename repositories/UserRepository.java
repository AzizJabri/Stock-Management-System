package repositories;

import models.Admin;
import models.User;
import database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository instance;
    private final Connection connection;

    private UserRepository() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public int save(User user) throws SQLException {
        String sql = "INSERT INTO user (full_name, age, phone_number) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getFullName());
            stmt.setInt(2, user.getAge());
            stmt.setInt(3, user.getPhoneNumber());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public User getById(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        Integer.parseInt(rs.getString("phone_number"))
                );
            }
        }
        return null;
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        Integer.parseInt(rs.getString("phone_number"))
                ));
            }
        }
        return users;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(Admin existingAdmin) throws SQLException {
        String sql = "UPDATE user SET full_name = ?, age = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, existingAdmin.getFullName());
            stmt.setInt(2, existingAdmin.getAge());
            stmt.setInt(3, existingAdmin.getPhoneNumber());
            stmt.setInt(4, existingAdmin.getId());
            stmt.executeUpdate();
        }
    }
}
