package services;

import interfaces.IOrderItemService;
import models.OrderItem;
import repositories.OrderItemRepository;
import repositories.OrderRepository;
import repositories.ProductRepository;

import java.util.ArrayList;

public class OrderItemService implements IOrderItemService {
    private static OrderItemService instance;
    private final OrderItemRepository orderItemRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private OrderItemService() {
        this.orderItemRepository = OrderItemRepository.getInstance();
        this.productRepository = ProductRepository.getInstance();
        this.orderRepository = OrderRepository.getInstance();
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
            // Check if order and product exist
            if (orderRepository.getById(orderId) == null) {
                System.out.println("Order not found");
                return;
            }
            if (productRepository.getById(productId) == null) {
                System.out.println("Product not found");
                return;
            }
            OrderItem item = new OrderItem(orderId, productId, quantity, unitPrice);
            orderItemRepository.save(item);
        } catch (Exception e) {
            System.out.println("Error adding order item: " + e.getMessage());
        }
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try {
            // Check if order and product exist
            if (orderRepository.getById(orderItem.getOrder_id()) == null) {
                System.out.println("Order not found");
                return;
            }
            if (productRepository.getById(orderItem.getProduct_id()) == null) {
                System.out.println("Product not found");
                return;
            }
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