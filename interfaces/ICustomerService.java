package interfaces;

import models.Customer;

import java.util.ArrayList;

public interface ICustomerService {
    ArrayList<Customer> list();
    void add(Customer customer);
    Customer getById(int id);
    void update(int id, Customer customer);
    void delete(int id);
}