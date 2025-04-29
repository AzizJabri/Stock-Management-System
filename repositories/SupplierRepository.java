package repositories;

import database.DBConnection;
import models.Admin;
import models.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {
    public static SupplierRepository instance ;
    public final Connection connection;


    private SupplierRepository () {
        this.connection = DBConnection.getInstance().getConnection();

    }
    public static SupplierRepository getInstance () {
        if (instance== null){
            instance = new SupplierRepository() ;
        }
           return instance;
    }

    public void save(Supplier supplier) throws SQLException {


        String sql = "INSERT INTO supplier (name, phone,address) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getPhone());
            stmt.setString(3, supplier.getAddress());
            stmt.executeUpdate();
        }
    }

    public Supplier getById(int id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }
        }
        return null;
    }

    public ArrayList<Supplier> getAll() throws SQLException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier ";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                       rs.getString("address")

                ));
            }
        }
        return suppliers;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(Supplier existingSupplier) throws SQLException {
        String sql = "UPDATE supplier SET name = ?, phone = ?, address = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, existingSupplier.getName());
            stmt.setString(2, existingSupplier.getPhone());
            stmt.setString(3, existingSupplier.getAddress());
            stmt.setInt(4, existingSupplier.getId());
            stmt.executeUpdate();
        }
    }
}




