package interfaces;

import models.Customer;

import java.util.ArrayList;

public interface ICustomerService {
    ArrayList<Customer> listCustomers();
    void addCustomer(Customer customer);
    Customer getCustomerById(int id);
    Customer getCustomerByEmail(String email);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}