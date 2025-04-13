package repositories;

import models.Order;
import database.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class OrderRepository {
    private static OrderRepository instance;
    private final Connection connection;

    private OrderRepository() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    public void save(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, order_date, status, total_amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getCustomer_id());
            stmt.setTimestamp(2, new Timestamp(order.getOrder_date().getTime()));
            stmt.setString(3, order.getStatus());
            stmt.setDouble(4, order.getTotal_amount());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
            }
        }
    }

    public Order getById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("order_date"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                );
            }
        }
        return null;
    }

    public ArrayList<Order> getAll() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("order_date"),
                        rs.getString("status"),
                        rs.getDouble("total_amount")
                ));
            }
        }
        return orders;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(Order order) throws SQLException {
        String sql = "UPDATE orders SET customer_id = ?, status = ?, total_amount = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomer_id());
            stmt.setString(2, order.getStatus());
            stmt.setDouble(3, order.getTotal_amount());
            stmt.setInt(4, order.getId());
            stmt.executeUpdate();
        }
    }
}