package controllers;

import services.AdminService;
import utils.PasswordUtils;
import models.Admin;

import java.util.Scanner;

public class AdminController {
    private static AdminController instance;
    private final AdminService adminService;

    private final Scanner scanner = new Scanner(System.in);

    // Private constructor to enforce singleton
    private AdminController() {
        adminService = AdminService.getInstance();
    }

    // Singleton access
    public static AdminController getInstance() {
        if (instance == null) {
            instance = new AdminController();
        }
        return instance;
    }

    // functions ta3 el controller
    public void listAdmins() {
        for (Admin admin : adminService.list()) {
            System.out.println("Admin ID: " + admin.getId() +
                    ", Username: " + admin.getUsername() +
                    ", Password: " + admin.getPassword()
                    + ", Full Name: " + admin.getFullName() +
                    ", Age: " + admin.getAge() +
                    ", Phone Number: " + admin.getPhoneNumber());
        }
    }

    public void registerAdmin() {
        try {
            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter phone number: ");
            int phoneNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String hashedPassword = PasswordUtils.hashPassword(password);
            Admin admin = new Admin(fullName, age, phoneNumber, username, hashedPassword);
            System.out.println("Admin created successfully.");

            adminService.add(admin);
        } catch (Exception e) {
            System.out.println("Error while registering admin: " + e.getMessage());
        }
    }

    public Admin loginAdmin() {
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            Admin admin = adminService.getByUsername(username);
            if (admin != null && PasswordUtils.verifyPassword(password, admin.getPassword())) {
                System.out.println("Login successful!");
                return admin;
            }
        } catch (Exception e) {
            System.out.println("Error while logging in: " + e.getMessage());
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public void updateAdmin() {
        try {
            System.out.print("Enter admin ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
            Admin admin = adminService.getById(id);
            System.out.print("Enter new username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            String hashedPassword = PasswordUtils.hashPassword(newPassword);
            admin.setUsername(newUsername);
            admin.setPassword(hashedPassword);
            adminService.update(id, admin);
            System.out.println("Admin updated successfully.");

        } catch (Exception e) {
            System.out.println("Error while updating admin: " + e.getMessage());
        }
    }

    public void deleteAdmin() {
        try {
            System.out.print("Enter admin ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());
            adminService.delete(id);
            System.out.println("Admin deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error while deleting admin: " + e.getMessage());
        }
    }

}
