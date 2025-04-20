package services;

import interfaces.ICustomerService;
import models.Customer;
import repositories.CustomerRepository;

import java.util.ArrayList;

public class CustomerService implements ICustomerService {
    private static CustomerService instance;
    private final CustomerRepository customerRepository;

    private CustomerService() {
        if (instance == null) {
            instance = this;
        }
        this.customerRepository = CustomerRepository.getInstance();
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    @Override
    public ArrayList<Customer> listCustomers() {
        try {
            return (ArrayList<Customer>) customerRepository.getAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            System.out.println("Error saving customer: " + e.getMessage());
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        try {
            return customerRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        try {
            return customerRepository.getByEmail(email);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            customerRepository.update(customer);
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    @Override
    public void deleteCustomer(int id) {
        try {
            customerRepository.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }
}
