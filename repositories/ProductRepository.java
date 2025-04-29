package repositories;

import database.DBConnection;
import models.Product;
import models.Supplier;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepository {
    public static ProductRepository instance;
    public final Connection connection;

    private ProductRepository() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO product (id, category_id, name, description, price, quantity_in_stock, supplier_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, product.getId());
            stmt.setInt(2, product.getCategory_id());
            stmt.setString(3, product.getName());
            stmt.setString(4, product.getDescription());
            stmt.setFloat(5, product.getPrice());
            stmt.setInt(6, product.getQuantity_in_stock());
            stmt.setInt(7, product.getSupplier_id());
            stmt.setDate(8, new java.sql.Date(product.getCreated_at().getTime()));
            stmt.setDate(9, new java.sql.Date(product.getUpdated_at().getTime()));

            stmt.executeUpdate();
        }
    }

    public Product getById(int id) throws SQLException {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getInt("quantity_in_stock"),
                        rs.getInt("supplier_id"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at")
                );
            }
        }
        return null;
    }

    public ArrayList<Product> getAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getInt("quantity_in_stock"),
                        rs.getInt("supplier_id"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at")
                ));
            }
        }
        return products;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(Product product) throws SQLException {
        String sql = "UPDATE product SET category_id = ?, name = ?, description = ?, price = ?, quantity_in_stock = ?, supplier_id = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, product.getCategory_id());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setFloat(4, product.getPrice());
            stmt.setInt(5, product.getQuantity_in_stock());
            stmt.setInt(6, product.getSupplier_id());
            stmt.setDate(7, new java.sql.Date(System.currentTimeMillis()));
            stmt.setInt(8, product.getId());
            stmt.executeUpdate();
        }
    }
}