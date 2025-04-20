package controllers;

import models.Order;
import services.OrderService;
import services.ProductService;

import java.util.ArrayList;
import java.util.Date;

public class OrderController {
    private static OrderController instance;
    private final OrderService orderService;

    private OrderController() {

        this.orderService = OrderService.getInstance();
    }

    public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }

    public void addOrder(int customerId, String status, double totalAmount) {
        Date now = new Date();
        orderService.addOrder(customerId, now, status, totalAmount);
    }

    public void updateOrder(Order order) {
        orderService.updateOrder(order);
    }

    public void deleteOrder(int orderId) {
        orderService.deleteOrder(orderId);
    }

    public Order getOrderById(int orderId) {
        return orderService.getOrderById(orderId);
    }

    public ArrayList<Order> listAllOrders() {
        return orderService.listAllOrders();
    }
}