package controllers;

import models.Customer;
import services.CustomerService;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController {
    private static CustomerController instance;
    private final CustomerService customerService;
    private final Scanner scanner = new Scanner(System.in);

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


    public void addCustomer() {
        try {
            System.out.println("Enter Customer full name: ");
            String fullName = scanner.nextLine();
            System.out.println("Enter Customer age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Customer phone number: ");
            int phoneNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Customer address: ");
            String address = scanner.nextLine();
            System.out.println("Enter Customer email: ");
            String email = scanner.nextLine();
            Customer customer = new Customer(fullName, age, phoneNumber, address, email);
            customerService.addCustomer(customer);
            System.out.println("Customer registered successfully.");

        } catch (Exception e) {
            System.out.println("Error while registering customer: " + e.getMessage());
        }
    }

    public Customer getCustomerById() {
        try {
            System.out.println("Enter Customer ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                return customer;
            } else {
                System.out.println("Customer not found.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error while fetching customer: " + e.getMessage());
            return null;
        }
    }

    public void updateCustomer() {
        try {
            Customer customer = getCustomerById();
            if (customer != null) {
                System.out.println("Enter new full name (leave blank to keep current): ");
                String fullName = scanner.nextLine();
                if (!fullName.isEmpty()) {
                    customer.setFullName(fullName);
                }
                System.out.println("Enter new age (leave blank to keep current): ");
                String ageInput = scanner.nextLine();
                if (!ageInput.isEmpty()) {
                    customer.setAge(Integer.parseInt(ageInput));
                }
                System.out.println("Enter new phone number (leave blank to keep current): ");
                String phoneNumberInput = scanner.nextLine();
                if (!phoneNumberInput.isEmpty()) {
                    customer.setPhoneNumber(Integer.parseInt(phoneNumberInput));
                }
                System.out.println("Enter new address (leave blank to keep current): ");
                String address = scanner.nextLine();
                if (!address.isEmpty()) {
                    customer.setAddress(address);
                }
                System.out.println("Enter new email (leave blank to keep current): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    customer.setEmail(email);
                }
                customerService.updateCustomer(customer);
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while updating customer: " + e.getMessage());
        }
    }

    public void deleteCustomer() {
        try {
            Customer customer = getCustomerById();
            if (customer != null) {
                customerService.deleteCustomer(customer.getId());
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while deleting customer: " + e.getMessage());
        }
    }

    public ArrayList<Customer> listCustomers() {
        try {
            ArrayList<Customer> customers = customerService.listCustomers();
            if (customers.isEmpty()) {
                System.out.println("No customers found.");
            } else {
                System.out.println("List of Customers:");
                for (Customer customer : customers) {
                    System.out.println("Customer ID: " + customer.getId());
                    System.out.println("Full Name: " + customer.getFullName());
                    System.out.println("Age: " + customer.getAge());
                    System.out.println("Phone Number: " + customer.getPhoneNumber());
                    System.out.println("Address: " + customer.getAddress());
                    System.out.println("Email: " + customer.getEmail());
                    System.out.println("-------------------------------------------------");
                }
            }
            return customers;
        } catch (Exception e) {
            System.out.println("Error while listing customers: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
