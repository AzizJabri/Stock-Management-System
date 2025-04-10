package controllers;

import services.AdminService;
import utils.PasswordUtils;
import models.Admin;

public class AdminController {
    private static AdminController instance;
    private final AdminService adminService;

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
                    ", Password: " + admin.getPassword());
        }
    }

    public void registerAdmin(String username, String password, String fullName, int age, int phoneNumber) {
        try {
            String hashedPassword = PasswordUtils.hashPassword(password);
            Admin admin = new Admin(fullName, age, phoneNumber, username, hashedPassword);

            adminService.add(admin);
        } catch (Exception e) {
            System.out.println("Error while registering admin: " + e.getMessage());
        }
    }

    public Admin loginAdmin(String username, String password) {
        try {
            Admin admin = adminService.getByUsername(username);
            if (admin != null && PasswordUtils.verifyPassword(password, admin.getPassword())) {
                return admin;
            }
        } catch (Exception e) {
            System.out.println("Error while logging in: " + e.getMessage());
        }
        return null;
    }

    public void updateAdmin(int id, String newUsername, String newPassword) {
        try {
            Admin admin = adminService.getById(id);
            if (admin != null) {
                String hashedPassword = PasswordUtils.hashPassword(newPassword);
                admin.setUsername(newUsername);
                admin.setPassword(hashedPassword);
                adminService.update(id, admin);
                System.out.println("Admin updated successfully.");
            } else {
                System.out.println("Admin not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while updating admin: " + e.getMessage());
        }
    }

    public void deleteAdmin(int id) {
        try {
            adminService.delete(id);
            System.out.println("Admin deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error while deleting admin: " + e.getMessage());
        }
    }

}
