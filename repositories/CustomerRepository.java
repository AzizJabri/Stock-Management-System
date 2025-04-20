package repositories;

import database.DBConnection;
import models.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerRepository {

    // Singleton instance
    private static CustomerRepository instance;
     private final Connection connection;
     private final UserRepository userRepository;

    private CustomerRepository() {
        this.connection = DBConnection.getInstance().getConnection();
        this.userRepository = UserRepository.getInstance();

    }

    public static CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }
        return instance;
    }

    public void save(Customer customer) throws SQLException {
        int userId = userRepository.save(customer);
        String sql = "INSERT INTO customer (id, email, address) VALUES (?, ?, ?)";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.executeUpdate();
        }

    }

    public ArrayList<Customer> getAll() throws SQLException {
        String sql = "SELECT u.*, c.email, c.address FROM customer c JOIN user u ON c.id = u.id";
        try (var stmt = connection.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        Integer.parseInt(rs.getString("phone_number")),
                        rs.getString("email"),
                        rs.getString("address")
                ));
            }
            return customers;
        }
    }

    public Customer getById(int id) throws SQLException {
        String sql = "SELECT u.*, c.email, c.address FROM customer c JOIN user u ON c.id = u.id WHERE c.id = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        Integer.parseInt(rs.getString("phone_number")),
                        rs.getString("email"),
                        rs.getString("address")
                );
            }
        }
        return null;
    }

    public Customer getByEmail(String email) throws SQLException {
        String sql = "SELECT u.*, c.email, c.address FROM customer c JOIN user u ON c.id = u.id WHERE c.email = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        Integer.parseInt(rs.getString("phone_number")),
                        rs.getString("email"),
                        rs.getString("address")
                );
            }
        }
        return null;
    }

    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET email = ?, address = ? WHERE id = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getEmail());
            stmt.setString(2, customer.getAddress());
            stmt.setInt(3, customer.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        userRepository.delete(id);
    }


}
