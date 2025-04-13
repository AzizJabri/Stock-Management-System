package services;

import interfaces.IOrderItemService;
import models.OrderItem;
import repositories.OrderItemRepository;
import java.util.ArrayList;

public class OrderItemService implements IOrderItemService {
    private static OrderItemService instance;
    private final OrderItemRepository orderItemRepository;

    private OrderItemService() {
        this.orderItemRepository = OrderItemRepository.getInstance();
    }

    public static OrderItemService getInstance() {
        if (instance == null) {
            instance = new OrderItemService();
        }
        return instance;
    }

    @Override
    public void addOrderItem(int orderId, int productId, int quantity, double unitPrice) {
        try {
            OrderItem item = new OrderItem(orderId, productId, quantity, unitPrice);
            orderItemRepository.save(item);
        } catch (Exception e) {
            System.out.println("Error adding order item: " + e.getMessage());
        }
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try {
            orderItemRepository.update(orderItem);
        } catch (Exception e) {
            System.out.println("Error updating order item: " + e.getMessage());
        }
    }

    @Override
    public void deleteOrderItem(int id) {
        try {
            orderItemRepository.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting order item: " + e.getMessage());
        }
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        try {
            return orderItemRepository.getById(id);
        } catch (Exception e) {
            System.out.println("Error getting order item: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<OrderItem> listAllOrderItems() {
        try {
            return orderItemRepository.getAll();
        } catch (Exception e) {
            System.out.println("Error listing order items: " + e.getMessage());
            return new ArrayList<>();
        }
    }


}