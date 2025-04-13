
import controllers.CategoryController;
import models.Admin;
import controllers.AdminController;
import utils.DisplayUtils;

import java.util.Scanner;
public class Main {
    static void createDefaultAdmin() {
        AdminController adminController = AdminController.getInstance();
        adminController.registerAdmin("admin", "admin123", "Admin User", 22, 123456789);
    }
    public static void main(String[] args){
        createDefaultAdmin();

        Scanner scanner = new Scanner(System.in);
        AdminController adminController = AdminController.getInstance();
        CategoryController categoryController = CategoryController.getInstance();
        Admin admin;

        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            admin = adminController.loginAdmin(username, password);
            if (admin == null) {
                System.out.println("Invalid username or password. Please try again.");
            } else {
                System.out.println("Login successful!");
                break;

            }
        }
        // application logic
        //1 manage admins
        //2 manage categories
        //3 manage suppliers
        //4 manage products
        //5 manage orders
        //6 manage customers
        //7 manage stock movements

        int choice = 0;
        while (choice > 8 || choice < 1) {
            DisplayUtils.diplayAdminDashboard(admin);
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    DisplayUtils.displayManageAdminMenu();
                    break;
                case 2:
                    DisplayUtils.displayManageCategoriesMenu();
                    break;
                case 3:
                    DisplayUtils.displayManageSuppliersMenu();
                    break;
                case 4:
                    DisplayUtils.displayManageProductsMenu();
                    break;
                case 5:
                    DisplayUtils.displayManageOrdersMenu();
                    break;
                case 6:
                    DisplayUtils.displayManageCustomersMenu();
                    break;
                case 7:
                    DisplayUtils.displayManageStockMovementsMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        // manage user input based on the choice

    }
}
