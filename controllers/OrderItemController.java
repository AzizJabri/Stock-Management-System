package controllers;

import models.Order;
import models.OrderItem;
import models.Product;
import services.OrderItemService;
import services.ProductService;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderItemController {
    private static OrderItemController instance;
    private final OrderItemService orderItemService;
    private OrderController orderController;
    private final ProductController productController;
    private final ProductService productService;

    private final Scanner scanner = new Scanner(System.in);

    private OrderItemController() {

        this.orderItemService = OrderItemService.getInstance();
        this.productController = ProductController.getInstance();
        this.productService = ProductService.getInstance();
    }

    private OrderController getOrderController() {
        if (orderController == null) {
            orderController = OrderController.getInstance();
        }
        return orderController;
    }


    public static OrderItemController getInstance() {
        if (instance == null) {
            instance = new OrderItemController();
        }
        return instance;
    }

    public void addOrderItem() {
        try {
            getOrderController().listAllOrders();
            Order order = getOrderController().getOrderById();
            if (order == null) {
                return;
            }
            productController.listProducts();
            Product product = productController.getProductById();
            if (product == null) {
                return;
            }
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                return;
            }
            if (product.getQuantity_in_stock() < quantity) {
                System.out.println("Insufficient stock for product: " + product.getName());
                return;
            }
            double price = product.getPrice() * quantity;

            orderItemService.addOrderItem(order.getId(), product.getId(), quantity, price);
            System.out.println("Order item added successfully.");
        } catch (Exception e) {
            System.out.println("Error while adding order item: " + e.getMessage());
        }
    }

    public void addOrderItemToOrder(int orderId) {
        try {
            productController.listProducts();
            Product product = productController.getProductById();
            if (product == null) {
                return;
            }
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                return;
            }
            if (product.getQuantity_in_stock() < quantity) {
                System.out.println("Insufficient stock for product: " + product.getName());
                return;
            }
            float price = product.getPrice() * quantity;
            orderItemService.addOrderItem(orderId, product.getId(), quantity, price);
            System.out.println("Order item added successfully to order ID: " + orderId);
        } catch (Exception e) {
            System.out.println("Error while adding order item to order: " + e.getMessage());
        }
    }

    public void updateOrderItem() {
        OrderItem orderItem = getOrderItemById();
        if (orderItem == null) {
            return;
        }
        System.out.print("Enter new quantity (leave blank to keep current or 0 to remove): ");
        String quantityInput = scanner.nextLine();
        if (!quantityInput.isEmpty()) {
            int quantity = Integer.parseInt(quantityInput);
            orderItem.setQuantity(quantity);
        }
        if (quantityInput.equals("0")) {
            orderItemService.deleteOrderItem(orderItem.getId());
            System.out.println("Order item removed successfully.");
            return;
        }
        double price = productService.getProductById(orderItem.getProduct_id()).getPrice() * Integer.parseInt(quantityInput);
        orderItem.setUnit_price(price);
        orderItemService.updateOrderItem(orderItem);
        System.out.println("Order item updated successfully.");
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

    public void listOrderItemsByOrderId(int orderId) {
        ArrayList<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        if (orderItems.isEmpty()) {
            System.out.println("No order items found for this order.");
        } else {
            for (OrderItem orderItem : orderItems) {
                System.out.println(orderItem);
            }
        }
    }
}