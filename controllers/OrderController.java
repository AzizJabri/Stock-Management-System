package controllers;

import models.Order;
import models.Supplier;
import services.OrderService;
import services.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderController {
    private static OrderController instance;
    private final OrderService orderService;

    private final Scanner scanner = new Scanner(System.in);

    private OrderController() {

        this.orderService = OrderService.getInstance();
    }

    public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }

    public void addOrder() {
        try {
            System.out.println("Entrer Order customerId: ");
            int customerId = Integer.parseInt(scanner.nextLine());
            System.out.println("Entrer Order status: ");
            String status = scanner.nextLine();
            System.out.println("Entrer Order totalAmount: ");
            double totalAmount = Double.parseDouble(scanner.nextLine());


            Date now = new Date();
            orderService.addOrder(customerId, now, status, totalAmount);


        } catch (Exception e) {
            System.out.println("Error while a adding product: " + e.getMessage());

        }

    }

    public void updateOrder() {
        System.out.println("Enter Order ID to update :");
        int id = Integer.parseInt(scanner.nextLine());
        Order order = orderService.getOrderById(id);
        if (order== null){
            System.out.println(
                    "Order not found"
            );
            return;
        }
        System.out.println("Entrer order customerId: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.println("Entrer Order status: ");
        String status = scanner.nextLine();
        System.out.println("Entrer Order totalAmount: ");
        double totalAmount = Double.parseDouble(scanner.nextLine());

        orderService.updateOrder(order);
    }

    public void deleteOrder() {
        System.out.println("Enter order ID to delete :");
        int id = Integer.parseInt(scanner.nextLine());
        Order order = orderService.getOrderById(id);
        if (order== null){
            System.out.println(
                    "Order not found"
            );
            return;
        }

        orderService.deleteOrder(id);
    }

    public Order getOrderById() {
        System.out.println("Enter order ID to delete :");
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

    public ArrayList<Order> listAllOrders() {
        return orderService.listAllOrders();
    }
}