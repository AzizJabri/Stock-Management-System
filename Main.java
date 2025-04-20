
import controllers.*;
import models.Admin;
import models.StockMovement;
import utils.DisplayUtils;

import java.util.Scanner;
public class Main {
    static void createDefaultAdmin() {
        AdminController adminController = AdminController.getInstance();
        adminController.registerAdmin();
    }
    public static void main(String[] args){
        //createDefaultAdmin();

        Scanner scanner = new Scanner(System.in);
        AdminController adminController = AdminController.getInstance();

        CategoryController categoryController = CategoryController.getInstance();
        ProductController productController = ProductController.getInstance();
        SupplierController supplierController = SupplierController.getInstance();
        StockMovementController stockMovementController = StockMovementController.getInstance();
        Admin admin = null;

        while (admin == null) {
            admin = adminController.loginAdmin();
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
        int sub_choice = 0;
        while (choice > 8 || choice < 1) {
            DisplayUtils.displayAdminDashboard(admin);
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    while (sub_choice != 5) {
                        DisplayUtils.displayManageAdminMenu();
                        sub_choice = Integer.parseInt(scanner.nextLine());
                        switch (sub_choice) {
                            case 1:
                                adminController.registerAdmin();
                                break;
                            case 2:
                                adminController.listAdmins();
                                adminController.updateAdmin();
                                break;
                            case 3:
                                adminController.listAdmins();
                                adminController.deleteAdmin();
                                break;
                            case 4:
                                adminController.listAdmins();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 2:
                    while (sub_choice != 5) {
                        DisplayUtils.displayManageCategoriesMenu();
                        sub_choice = Integer.parseInt(scanner.nextLine());
                        switch (sub_choice) {
                            case 1:
                                categoryController.addCategory();
                                break;
                            case 2:
                                categoryController.listCategories();
                                categoryController.updateCategory();
                                break;
                            case 3:
                                categoryController.listCategories();
                                categoryController.deleteCategory();
                                break;
                            case 4:
                                categoryController.listCategories();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 3:
                    while (sub_choice != 5) {
                        DisplayUtils.displayManageSuppliersMenu();
                        sub_choice = Integer.parseInt(scanner.nextLine());
                        switch (sub_choice) {
                            case 1:
                                supplierController.addSupplier();
                                break;
                            case 2:
                                supplierController.listSuppliers();
                                supplierController.updateSupplier();
                                break;
                            case 3:
                                supplierController.listSuppliers();
                                supplierController.deleteSupplier();
                                break;
                            case 4:
                                supplierController.listSuppliers();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 4:
                    DisplayUtils.displayManageProductsMenu();
                    sub_choice = Integer.parseInt(scanner.nextLine());
                    switch (sub_choice) {
                        case 1:
                            productController.addProduct();
                            break;
                        case 2:
                            productController.listProducts();
                            productController.updateProduct();
                            break;
                        case 3:
                            productController.listProducts();
                            productController.deleteProduct();
                            break;
                        case 4:
                            productController.listProducts();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                case 5:
                    DisplayUtils.displayManageOrdersMenu();
                    break;
                case 6:
                    DisplayUtils.displayManageCustomersMenu();
                    break;
                case 7:
                    while (sub_choice != 5) {
                        DisplayUtils.displayManageStockMovementsMenu();
                        sub_choice = Integer.parseInt(scanner.nextLine());
                        switch (sub_choice) {
                            case 1:
                                stockMovementController.recordMovement();
                                break;
                            case 2:
                                stockMovementController.getAllMovements();
                                stockMovementController.updateMovement();
                                break;
                            case 3:
                                stockMovementController.getAllMovements();
                                stockMovementController.removeMovement();
                                break;
                            case 4:
                                stockMovementController.getAllMovements();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        // manage user input based on the choice

    }
}
