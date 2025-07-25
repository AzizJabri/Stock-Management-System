package utils;

import models.Admin;

public class DisplayUtils {

    public static void displayAdminDashboard(Admin admin) {
        System.out.println("Welcome to the Dashboard " + admin.getFullName());
        System.out.println("1. Manage Admins");
        System.out.println("2. Manage Categories");
        System.out.println("3. Manage Suppliers");
        System.out.println("4. Manage Products");
        System.out.println("5. Manage Orders");
        System.out.println("6. Manage Customers");
        System.out.println("7. Manage Stock Movements");
        System.out.println("8. Exit");
    }

    public static void displayManageAdminMenu() {
        System.out.println("1. Add Admin");
        System.out.println("2. Update Admin");
        System.out.println("3. Delete Admin");
        System.out.println("4. List Admins");
        System.out.println("5. Exit");
    }

    public static void displayManageCategoriesMenu() {
        System.out.println("1. Add Category");
        System.out.println("2. Update Category");
        System.out.println("3. Delete Category");
        System.out.println("4. List Categories");
        System.out.println("5. Exit");
    }

    public static void displayManageSuppliersMenu() {
        System.out.println("1. Add Supplier");
        System.out.println("2. Update Supplier");
        System.out.println("3. Delete Supplier");
        System.out.println("4. List Suppliers");
        System.out.println("5. Exit");
    }

    public static void displayManageProductsMenu() {
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. List Products");
        System.out.println("5. Exit");
    }

    public static void displayManageOrdersMenu() {
        System.out.println("1. Add Order");
        System.out.println("2. Update Order");
        System.out.println("3. Delete Order");
        System.out.println("4. List Orders");
        System.out.println("5. Exit");
    }

    public static void displayManageCustomersMenu() {
        System.out.println("1. Add Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. List Customers");
        System.out.println("5. Exit");
    }

    public static void displayManageStockMovementsMenu() {
        System.out.println("1. Add Stock Movement");
        System.out.println("2. Update Stock Movement");
        System.out.println("3. Delete Stock Movement");
        System.out.println("4. List Stock Movements");
        System.out.println("5. Exit");
    }


}
