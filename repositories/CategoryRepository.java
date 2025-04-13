package repositories;

import database.DBConnection;
import models.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static CategoryRepository instance;
    private final Connection connection;

    private CategoryRepository() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public static synchronized CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }

    public ArrayList<Category> getAll() throws SQLException {
        String sql = "SELECT * FROM category";
        try (var stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<Category> categories = new ArrayList<>();
            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
            return categories;
        }

    }

    public Category getById(int id) throws SQLException{
        String sql = "SELECT * FROM category WHERE id = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
        }
        return null;
    }

    public void add(String name, String description) throws SQLException {
        String sql = "INSERT INTO category (name, description) VALUES (?, ?)";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.executeUpdate();
        }
    }

    public void update(Category category) throws SQLException {
        String sql = "UPDATE category SET name = ?, description = ? WHERE id = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM category WHERE id = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }



}
