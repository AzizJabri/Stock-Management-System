package controllers;

import models.OrderItem;
import services.OrderItemService;
import java.util.ArrayList;

public class OrderItemController {
    private static OrderItemController instance;
    private final OrderItemService orderItemService;

    private OrderItemController() {

        this.orderItemService = OrderItemService.getInstance();
    }

    public static OrderItemController getInstance() {
        if (instance == null) {
            instance = new OrderItemController();
        }
        return instance;
    }

    public void addOrderItem(int orderId, int productId, int quantity, double unitPrice) {
        orderItemService.addOrderItem(orderId, productId, quantity, unitPrice);
    }

    public void updateOrderItem(OrderItem orderItem) {
        orderItemService.updateOrderItem(orderItem);
    }

    public void deleteOrderItem(int id) {
        orderItemService.deleteOrderItem(id);
    }

    public OrderItem getOrderItemById(int id) {
        return orderItemService.getOrderItemById(id);
    }

    public ArrayList<OrderItem> listAllOrderItems() {
        return orderItemService.listAllOrderItems();
    }
}