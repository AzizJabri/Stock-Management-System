package interfaces;

import models.Order;
import java.util.ArrayList;
import java.util.Date;

public interface IOrderService {
    void addOrder(int customerId, Date orderDate, String status, double totalAmount);
    void updateOrder(Order order);
    void deleteOrder(int id);
    Order getOrderById(int id);
    ArrayList<Order> listAllOrders();
}