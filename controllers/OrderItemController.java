package controllers;

import models.OrderItem;
import services.OrderItemService;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderItemController {
    private static OrderItemController instance;
    private final OrderItemService orderItemService;

    private final Scanner scanner = new Scanner(System.in);

    private OrderItemController() {

        this.orderItemService = OrderItemService.getInstance();
    }

    public static OrderItemController getInstance() {
        if (instance == null) {
            instance = new OrderItemController();
        }
        return instance;
    }

    public void addOrderItem() {
        try {
            System.out.print("Enter order ID: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter product ID: ");
            int productId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());

            orderItemService.addOrderItem(orderId, productId, quantity, price);
            System.out.println("Order item added successfully.");
        } catch (Exception e) {
            System.out.println("Error while adding order item: " + e.getMessage());
        }
    }

    public void updateOrderItem() {
        OrderItem orderItem = getOrderItemById();
        if (orderItem == null) {
            System.out.println("Order item not found.");
            return;
        }
        System.out.print("Enter new quantity (leave blank to keep current): ");
        String quantityInput = scanner.nextLine();
        if (!quantityInput.isEmpty()) {
            int quantity = Integer.parseInt(quantityInput);
            orderItem.setQuantity(quantity);
        }
        System.out.print("Enter new price (leave blank to keep current): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            double price = Double.parseDouble(priceInput);
            orderItem.setUnit_price(price);
        }
        if (quantityInput.isEmpty() && priceInput.isEmpty()) {
            System.out.println("No changes made.");
            return;
        }
        orderItemService.updateOrderItem(orderItem);
    }

    public void deleteOrderItem() {
        OrderItem orderItem = getOrderItemById();
        if (orderItem == null) {
            System.out.println("Order item not found.");
            return;
        }
        orderItemService.deleteOrderItem(orderItem.getId());

    }

    public OrderItem getOrderItemById() {
        System.out.print("Enter order item ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        if (orderItem == null) {
            System.out.println("Order item not found.");
        }
        return orderItem;
    }

    public void listOrderItems() {
        ArrayList<OrderItem> orderItems = orderItemService.listAllOrderItems();
        if (orderItems.isEmpty()) {
            System.out.println("No order items found.");
        } else {
            for (OrderItem orderItem : orderItems) {
                System.out.println(orderItem);
            }
        }
    }
}