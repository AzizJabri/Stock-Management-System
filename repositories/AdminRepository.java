package repositories;

import models.Admin;
import database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private static AdminRepository instance;
    private final Connection connection;
    private final UserRepository userRepository;

    private AdminRepository() {
        this.connection = DBConnection.getInstance().getConnection();
        this.userRepository = UserRepository.getInstance();
    }

    public static synchronized AdminRepository getInstance() {
        if (instance == null) {
            instance = new AdminRepository();
        }
        return instance;
    }

    public void save(Admin admin) throws SQLException {
        int userId = userRepository.save(admin);

        String sql = "INSERT INTO admin (id, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, admin.getUsername());
            stmt.setString(3, admin.getPassword());
            stmt.executeUpdate();
        }
    }

    public Admin getByUsername(String username) throws SQLException {
        String sql = "SELECT a.*, u.full_name, u.age, u.phone_number FROM admin a JOIN user u ON a.id = u.id WHERE a.username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        Integer.parseInt(rs.getString("phone_number")),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        }
        return null;
    }

    public List<Admin> getAll() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT a.*, u.full_name, u.age, u.phone_number FROM admin a JOIN user u ON a.id = u.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        Integer.parseInt(rs.getString("phone_number")),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        }
        return admins;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM admin WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        userRepository.delete(id);
    }

    public Admin getById(int id) throws SQLException {
            String sql = "SELECT a.*, u.full_name, u.age, u.phone_number FROM admin a JOIN user u ON a.id = u.id WHERE a.id = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Admin(
                                rs.getInt("id"),
                                rs.getString("full_name"),
                                rs.getInt("age"),
                                Integer.parseInt(rs.getString("phone_number")),
                                rs.getString("username"),
                                rs.getString("password")
                        );
                    }
                }
            }
            return null;
        }

    public void update(Admin existingAdmin) throws SQLException {
        String sql = "UPDATE admin SET username = ?, password = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, existingAdmin.getUsername());
            stmt.setString(2, existingAdmin.getPassword());
            stmt.setInt(3, existingAdmin.getId());
            stmt.executeUpdate();
        }

        userRepository.update(existingAdmin);
    }
}
