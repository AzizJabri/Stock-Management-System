package services;

import interfaces.IAdminService;
import models.Admin;
import repositories.AdminRepository;

import java.util.ArrayList;

public class AdminService implements IAdminService {
    private static AdminService instance;
    private final AdminRepository adminRepository;

    private AdminService() {
        if (instance == null) {
            instance = this;
        }
        this.adminRepository = AdminRepository.getInstance();
    }

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    @Override
    public ArrayList<Admin> list() {
        try {
            return (ArrayList<Admin>) adminRepository.getAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void add(Admin admin) {
        try {
            adminRepository.save(admin);
        } catch (Exception e) {
            System.out.println("Error saving admin: " + e.getMessage());
        }
    }

    @Override
    public Admin getById(int id) {
        try {
            return adminRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Admin getByUsername(String username) {
        try {
            return adminRepository.getByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(int id, Admin updatedAdmin) {
        try {
            Admin existingAdmin = adminRepository.getById(id);
            if (existingAdmin != null) {
                existingAdmin.setFullName(updatedAdmin.getFullName());
                existingAdmin.setAge(updatedAdmin.getAge());
                existingAdmin.setPhoneNumber(updatedAdmin.getPhoneNumber());
                existingAdmin.setUsername(updatedAdmin.getUsername());
                existingAdmin.setPassword(updatedAdmin.getPassword());
                adminRepository.update(existingAdmin);
            }
        } catch (Exception e) {
            System.out.println("Error updating admin: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            adminRepository.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting admin: " + e.getMessage());
        }
    }
}
