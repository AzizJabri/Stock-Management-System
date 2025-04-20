package controllers;

import models.Supplier;
import services.SupplierService;

import java.util.ArrayList;
import java.util.Scanner;

public class SupplierController {
    private static SupplierController instance;
    private final SupplierService supplierService;

    private final Scanner scanner = new Scanner(System.in);

    private SupplierController() {
        this.supplierService = SupplierService.getInstance();
    }

    public static SupplierController getInstance() {
        if (instance == null) {
            instance = new SupplierController();
        }
        return instance;
    }

    public void addSupplier() {
        try {
            System.out.println("Enter supplier name: ");
            String name = scanner.nextLine();
            System.out.println("Enter supplier phone: ");
            String phone = scanner.nextLine();

            System.out.println("Enter supplier address: ");
            String address = scanner.nextLine();

            supplierService.addSupplier(name, phone, address);

        } catch (Exception e) {
            System.out.println("Error while a adding supplier: " + e.getMessage());

        }

    }
    public void updateSupplier() {
        Supplier supplier = getSupplierById();
        if (supplier== null){
            return;
        }
        System.out.println("Enter new name :");
        String name = scanner.nextLine();
        System.out.println("Enter new phone :");
        String phone = scanner.nextLine();
        System.out.println("Enter new address :");
        String address = scanner.nextLine();

        if (!name.isEmpty()) {
            supplier.setName(name);
        }
        if (!phone.isEmpty()) {
            supplier.setPhone(phone);
        }
        if (!address.isEmpty()) {
            supplier.setAddress(address);
        }
        if (name.isEmpty() && phone.isEmpty() && address.isEmpty()) {
            System.out.println("No changes made.");
            return;
        }

        supplierService.updateSupplier(supplier);
        System.out.println("Supplier updated successfully.");
    }

    public void deleteSupplier() {
        Supplier supplier = getSupplierById();
        if (supplier== null){
            return;
        }
        supplierService.deleteSupplier(supplier.getId());

    }

    public Supplier getSupplierById() {
        System.out.println("Enter supplier ID :");
        int id = Integer.parseInt(scanner.nextLine());
        Supplier supplier = supplierService.getSupplierById(id);
        if (supplier== null){
            System.out.println("Supplier not found!");
            return null;
        }
        return supplier;
    }

    public void listSuppliers() {
        ArrayList<Supplier> suppliers = supplierService.listSuppliers();
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers found.");
        } else {
            System.out.println("List of suppliers:");
            for (Supplier supplier : suppliers) {
                System.out.println(supplier);
            }
        }
    }
}
