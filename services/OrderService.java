package services;

import interfaces.IOrderService;
import models.Order;
import repositories.OrderRepository;

import java.util.ArrayList;
import java.util.Date;

public class OrderService implements IOrderService {
    private static OrderService instance;
    private final OrderRepository orderRepository;

    private OrderService() {
        this.orderRepository = OrderRepository.getInstance();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    @Override
    public void addOrder(int customerId, Date orderDate, String status, double totalAmount) {
        try {
            Order order = new Order(customerId, orderDate, status, totalAmount);
            orderRepository.save(order);
        } catch (Exception e) {
            System.out.println("Error adding Order: " + e.getMessage());
        }
    }

    @Override
    public void updateOrder(Order order) {
        try {
            orderRepository.update(order);
        } catch (Exception e) {
            System.out.println("Error updating Order: " + e.getMessage());
        }
    }

    @Override
    public void deleteOrder(int id) {
        try {
            Order order = orderRepository.getById(id);
            if (order != null) {
                order.setStatus("cancelled");
                orderRepository.update(order);
            }
        } catch (Exception e) {
            System.out.println("Error deleting Order: " + e.getMessage());
        }
    }

    @Override
    public Order getOrderById(int id) {
        try {
            return orderRepository.getById(id);
        } catch (Exception e) {
            System.out.println("Error getting Order: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Order> listAllOrders() {
        try {
            return orderRepository.getAll();
        } catch (Exception e) {
            System.out.println("Error listing Orders: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}