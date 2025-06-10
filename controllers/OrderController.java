package controllers;

import models.*;
import services.CustomerService;
import services.OrderItemService;
import services.OrderService;
import services.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderController {
    private static OrderController instance;
    private final OrderService orderService;

    private final CustomerController customerController;
    private final OrderItemController orderItemController;
    private final OrderItemService orderItemService;
    private final ProductService productService;


    private final Scanner scanner = new Scanner(System.in);

    private OrderController() {

        this.orderService = OrderService.getInstance();
        this.customerController = CustomerController.getInstance();
        this.orderItemController = OrderItemController.getInstance();
        this.orderItemService = OrderItemService.getInstance();
        this.productService = ProductService.getInstance();
    }

    public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }

    public void addOrder() {
        try {
            customerController.listCustomers();
            Customer customer = customerController.getCustomerById();
            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            String status;
            System.out.println("Enter order status (Pending, Completed):");
            // Validate status input
            while (true) {
                status = scanner.nextLine().trim();
                if (status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Completed")) {
                    break;
                } else {
                    System.out.println("Invalid status. Please enter 'Pending' or 'Completed':");
                }
            }

            Date now = new Date();


            int orderId = orderService.addOrder(customer.getId(), now, status, 0.0);
            System.out.println("Order added successfully with ID: " + orderId);


            System.out.println("Do you want to add order items? (yes/no):");
            String response = scanner.nextLine().trim().toLowerCase();
            while (response.equals("yes")) {
                orderItemController.addOrderItemToOrder(orderId);
                System.out.println("Add another item? (yes/no):");
                response = scanner.nextLine().trim().toLowerCase();
            }

            // Recalculate the total
            double total = 0.0;
            ArrayList<OrderItem> items = orderItemService.getOrderItemsByOrderId(orderId);
            for (OrderItem item : items) {
                total += item.getUnit_price();
                if(status.equalsIgnoreCase("Completed")) {
                    // Update product stock if order is completed
                    Product product = productService.getProductById(item.getProduct_id());
                    if (product != null) {
                        int newStock = product.getQuantity_in_stock() - item.getQuantity();
                        if (newStock < 0) {
                            System.out.println("Insufficient stock for product: " + product.getName());
                        } else {
                            product.setQuantity_in_stock(newStock);
                            productService.updateProduct(product);
                            System.out.println("Updated stock for product: " + product.getName() + " to " + newStock);
                        }
                    } else {
                        System.out.println("Product not found for ID: " + item.getProduct_id());
                    }
                }
            }

            // Update the order with the correct total
            Order updatedOrder = orderService.getOrderById(orderId);
            updatedOrder.setTotal_amount(total);
            orderService.updateOrder(updatedOrder);
            System.out.println("Order added successfully with ID: " + orderId);

            System.out.println("Order total updated to: " + total);
        } catch (Exception e) {
            System.out.println("Error while adding order: " + e.getMessage());
        }
    }


    public void updateOrder() {
        Order order = getOrderById();
        if (order == null) {
            return;
        }

        Customer customer = customerController.getCustomerById();
        if (customer == null) {
            return;
        }
        String status;
        System.out.println("Current order status: " + order.getStatus());
        System.out.println("Enter new order status (Pending, Completed):");
        // Validate status input
        while (true) {
            status = scanner.nextLine().trim();
            if (status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Completed")) {
                break;
            } else {
                System.out.println("Invalid status. Please enter 'Pending' or 'Completed':");
            }
        }

        System.out.println("Enter new total amount: (leave blank to calculate automatically)");
        double totalAmount = 0.0;
        String totalInput = scanner.nextLine().trim();
        if (!totalInput.isEmpty()) {
            try {
                totalAmount = Double.parseDouble(totalInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid total amount. Using automatic calculation.");
            }
        }
        if (totalAmount <= 0) {
            totalAmount = 0.0; // Default to 0 if invalid input
        }


        order.setCustomer_id(customer.getId());
        order.setOrder_date(new Date());
        order.setStatus(status);
        order.setTotal_amount(totalAmount);
        orderService.updateOrder(order);
        System.out.println("Order updated successfully.");

        // Manage order items
        System.out.println("Do you want to manage order items? (yes/no):");
        String manage = scanner.nextLine().trim().toLowerCase();
        while (manage.equals("yes")) {
            System.out.println("Choose an option: [1] Add Item  [2] Update Item  [3] Delete Item  [4] List Items  [5] Exit");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    orderItemController.addOrderItemToOrder(order.getId());
                    break;
                case "2":
                    orderItemController.listOrderItemsByOrderId(order.getId());
                    orderItemController.updateOrderItem();
                    break;
                case "3":
                    orderItemController.listOrderItemsByOrderId(order.getId());
                    orderItemController.deleteOrderItem();
                    break;
                case "4":
                    orderItemController.listOrderItemsByOrderId(order.getId());
                    break;
                case "5":
                    manage = "no";
                    break;
                default:
                    System.out.println("Invalid option.");
            }

            if (!manage.equals("no")) {
                System.out.println("Do you want to continue managing items? (yes/no):");
                manage = scanner.nextLine().trim().toLowerCase();
            }
        }

        // Recalculate the total after managing items
        double total = 0.0;
        ArrayList<OrderItem> items = orderItemService.getOrderItemsByOrderId(order.getId());
        for (OrderItem item : items) {
            total += item.getUnit_price();
            if(status.equalsIgnoreCase("Completed")) {
                // Update product stock if order is completed
                Product product = productService.getProductById(item.getProduct_id());
                if (product != null) {
                    int newStock = product.getQuantity_in_stock() - item.getQuantity();
                    if (newStock < 0) {
                        System.out.println("Insufficient stock for product: " + product.getName());
                    } else {
                        product.setQuantity_in_stock(newStock);
                        productService.updateProduct(product);
                        System.out.println("Updated stock for product: " + product.getName() + " to " + newStock);
                    }
                } else {
                    System.out.println("Product not found for ID: " + item.getProduct_id());
                }
            }
        }

        if(totalInput.isEmpty()) {
            order.setTotal_amount(total);
            orderService.updateOrder(order);
            System.out.println("Order total updated to: " + total);
        } else {
            System.out.println("No items found in the order. Total remains 0.");
        }
    }


    public void deleteOrder() {
        Order order = getOrderById();
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        orderService.deleteOrder(order.getId());
        System.out.println("Order deleted successfully.");
    }

    public Order getOrderById() {
        System.out.println("Enter order ID:");
        int id = Integer.parseInt(scanner.nextLine());
        Order order = orderService.getOrderById(id);
        if (order== null){
            System.out.println(
                    "Order not found"
            );
            return null;
        }

        return orderService.getOrderById(id);
    }

    public void listAllOrders() {
        ArrayList<Order> orders = orderService.listAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("List of all orders:");
            for (Order order : orders) {
                System.out.println(
                        "Order ID: " + order.getId() +
                        ", Customer ID: " + order.getCustomer_id() +
                        ", Order Date: " + order.getOrder_date() +
                        ", Status: " + order.getStatus() +
                        ", Total Amount: " + order.getTotal_amount()
                );
            }
        }
    }
}