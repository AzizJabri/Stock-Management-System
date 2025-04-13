package repositories;

import models.OrderItem;
import database.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class OrderItemRepository {
    private static OrderItemRepository instance;
    private final Connection connection;

    private OrderItemRepository() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public static OrderItemRepository getInstance() {
        if (instance == null) {
            instance = new OrderItemRepository();
        }
        return instance;
    }

    public ArrayList<OrderItem> getAll() throws SQLException {
        ArrayList<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_item";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                ));
            }
        }
        return items;
    }

    public void save(OrderItem orderItem) throws SQLException {
        String sql = "INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderItem.getOrder_id());
            stmt.setInt(2, orderItem.getProduct_id());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getUnit_price());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    orderItem.setId(rs.getInt(1));
                }
            }
        }
    }

    public OrderItem getById(int id) throws SQLException {
        String sql = "SELECT * FROM order_item WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                );
            }
        }
        return null;
    }

    public ArrayList<OrderItem> getByOrderId(int orderId) throws SQLException {
        ArrayList<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_item WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                ));
            }
        }
        return items;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM order_item WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(OrderItem orderItem) throws SQLException {
        String sql = "UPDATE order_item SET quantity = ?, unit_price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderItem.getQuantity());
            stmt.setDouble(2, orderItem.getUnit_price());
            stmt.setInt(3, orderItem.getId());
            stmt.executeUpdate();
        }
    }
}