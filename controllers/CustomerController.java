package controllers;

import models.Customer;
import services.CustomerService;

import java.util.ArrayList;

public class CustomerController {
    private static CustomerController instance;
    private final CustomerService customerService;

    // Private constructor to enforce singleton
    private CustomerController() {
        customerService = CustomerService.getInstance();
    }

    // Singleton access
    public static CustomerController getInstance() {
        if (instance == null) {
            instance = new CustomerController();
        }
        return instance;
    }


    public void registerCustomer(String fullName, int age, int phoneNumber, String username, String password) {
        try {
            Customer customer = new Customer(fullName, age, phoneNumber, username, password);
            customerService.addCustomer(customer);
        } catch (Exception e) {
            System.out.println("Error while registering customer: " + e.getMessage());
        }
    }

    public Customer getCustomerById(int id) {
        try {
            return customerService.getCustomerById(id);
        } catch (Exception e) {
            System.out.println("Error while fetching customer: " + e.getMessage());
            return null;
        }
    }

    public void updateCustomer(int id, Customer updatedCustomer) {
        try {
            updatedCustomer.setId(id); // Ensure the ID is set for the update
            customerService.updateCustomer(updatedCustomer);
        } catch (Exception e) {
            System.out.println("Error while updating customer: " + e.getMessage());
        }
    }

    public void deleteCustomer(int id) {
        try {
            customerService.deleteCustomer(id);
        } catch (Exception e) {
            System.out.println("Error while deleting customer: " + e.getMessage());
        }
    }

    public ArrayList<Customer> listCustomers() {
        try {
            return customerService.listCustomers();
        } catch (Exception e) {
            System.out.println("Error while listing customers: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
