import controllers.*;
import models.Admin;
import utils.DisplayUtils;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static AdminController adminController = AdminController.getInstance();
    static CategoryController categoryController = CategoryController.getInstance();
    static ProductController productController = ProductController.getInstance();
    static SupplierController supplierController = SupplierController.getInstance();
    static StockMovementController stockMovementController = StockMovementController.getInstance();
    static OrderController orderController = OrderController.getInstance();
    static OrderItemController orderItemController = OrderItemController.getInstance();

    public static void main(String[] args) {
        Admin admin = null;
        while (admin == null) {
            admin = adminController.loginAdmin();
        }

        while (true) {
            DisplayUtils.displayAdminDashboard(admin);
            int choice = getIntInput("Enter your choice:");

            switch (choice) {
                case 1 -> manageAdmins();
                case 2 -> manageCategories();
                case 3 -> manageSuppliers();
                case 4 -> manageProducts();
                case 5 -> manageOrders();
                case 6 -> DisplayUtils.displayManageCustomersMenu(); // Placeholder
                case 7 -> manageStockMovements();
                case 8 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageAdmins() {
        int choice;
        do {
            DisplayUtils.displayManageAdminMenu();
            choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1 -> adminController.registerAdmin();
                case 2 -> {
                    adminController.listAdmins();
                    adminController.updateAdmin();
                }
                case 3 -> {
                    adminController.listAdmins();
                    adminController.deleteAdmin();
                }
                case 4 -> adminController.listAdmins();
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void manageCategories() {
        int choice;
        do {
            DisplayUtils.displayManageCategoriesMenu();
            choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1 -> categoryController.addCategory();
                case 2 -> {
                    categoryController.listCategories();
                    categoryController.updateCategory();
                }
                case 3 -> {
                    categoryController.listCategories();
                    categoryController.deleteCategory();
                }
                case 4 -> categoryController.listCategories();
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void manageSuppliers() {
        int choice;
        do {
            DisplayUtils.displayManageSuppliersMenu();
            choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1 -> supplierController.addSupplier();
                case 2 -> {
                    supplierController.listSuppliers();
                    supplierController.updateSupplier();
                }
                case 3 -> {
                    supplierController.listSuppliers();
                    supplierController.deleteSupplier();
                }
                case 4 -> supplierController.listSuppliers();
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void manageProducts() {
        int choice;
        do {
            DisplayUtils.displayManageProductsMenu();
            choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1 -> productController.addProduct();
                case 2 -> {
                    productController.listProducts();
                    productController.updateProduct();
                }
                case 3 -> {
                    productController.listProducts();
                    productController.deleteProduct();
                }
                case 4 -> productController.listProducts();
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void manageOrders() {
        int choice;
        do {
            DisplayUtils.displayManageOrdersMenu();
            choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1 -> orderController.addOrder();
                case 2 -> {
                    orderController.listAllOrders();
                    orderController.updateOrder();
                }
                case 3 -> {
                    orderController.listAllOrders();
                    orderController.deleteOrder();
                }
                case 4 -> orderController.listAllOrders();
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void manageStockMovements() {
        int choice;
        do {
            DisplayUtils.displayManageStockMovementsMenu();
            choice = getIntInput("Enter your choice:");
            switch (choice) {
                case 1 -> stockMovementController.recordMovement();
                case 2 -> {
                    stockMovementController.getAllMovements();
                    stockMovementController.updateMovement();
                }
                case 3 -> {
                    stockMovementController.getAllMovements();
                    stockMovementController.removeMovement();
                }
                case 4 -> stockMovementController.getAllMovements();
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt + " ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. " + prompt + " ");
            }
        }
    }
}
