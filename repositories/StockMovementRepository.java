package repositories;

import models.MovementType;
import models.StockMovement;
import database.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class StockMovementRepository {
    private static StockMovementRepository instance;
    private final Connection connection;

    private StockMovementRepository() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public static StockMovementRepository getInstance() {
        if (instance == null) {
            instance = new StockMovementRepository();
        }
        return instance;
    }

    public void save(StockMovement movement) throws SQLException {
        String sql = "INSERT INTO stock_movement (product_id, quantity, movement_type, date, reference) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, movement.getProduct_id());
            stmt.setInt(2, movement.getQuantity());
            stmt.setString(3, movement.getMovement_type().name());
            stmt.setTimestamp(4, new Timestamp(movement.getDate().getTime()));
            stmt.setString(5, movement.getReference());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    movement.setId(rs.getInt(1));
                }
            }
        }
    }

    public StockMovement getById(int id) throws SQLException {
        String sql = "SELECT * FROM stock_movement WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new StockMovement(
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        MovementType.valueOf(rs.getString("movement_type")),
                        new Date(rs.getTimestamp("date").getTime()),
                        rs.getString("reference")
                ) {{
                    setId(rs.getInt("id"));
                }};
            }
        }
        return null;
    }

    public ArrayList<StockMovement> getAll() throws SQLException {
        ArrayList<StockMovement> movements = new ArrayList<>();
        String sql = "SELECT * FROM stock_movement";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StockMovement movement = new StockMovement(
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        MovementType.valueOf(rs.getString("movement_type")),
                        new Date(rs.getTimestamp("date").getTime()),
                        rs.getString("reference")
                );
                movement.setId(rs.getInt("id"));
                movements.add(movement);
            }
        }
        return movements;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM stock_movement WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(StockMovement movement) throws SQLException {
        String sql = "UPDATE stock_movement SET product_id = ?, quantity = ?, movement_type = ?, date = ?, reference = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movement.getProduct_id());
            stmt.setInt(2, movement.getQuantity());
            stmt.setString(3, movement.getMovement_type().toString());
            stmt.setTimestamp(4, new Timestamp(movement.getDate().getTime()));
            stmt.setString(5, movement.getReference());
            stmt.setInt(6, movement.getId());
            stmt.executeUpdate();
        }
    }
}