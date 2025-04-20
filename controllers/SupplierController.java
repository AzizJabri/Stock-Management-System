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
            System.out.println("Entrer supplier name: ");
            String name = scanner.nextLine();
            System.out.println("Entrer supplier phone: ");
            String phone = scanner.nextLine();

            System.out.println("Entrer supplier address: ");
            String address = scanner.nextLine();

            supplierService.addSupplier(name, phone, address);

        } catch (Exception e) {
            System.out.println("Error while a adding supplier: " + e.getMessage());

        }

    }
    public void updateSupplier() {
        System.out.println("Enter supplier ID to update :");
        int id = Integer.parseInt(scanner.nextLine());
        Supplier supplier = supplierService.getSupplierById(id);
        if (supplier== null){
            System.out.println(
                    "Supplier not found"
            );
            return;
        }
        System.out.println("Enter new name :");
        String name = scanner.nextLine();
        System.out.println("Enter new phone :");
        String phone = scanner.nextLine();
        System.out.println("Enter new address :");
        String address = scanner.nextLine();
        supplierService.updateSupplier(supplier);
    }

    public void deleteSupplier() {
        System.out.println("Enter supplier ID to delete :");
        int id = Integer.parseInt(scanner.nextLine());
        Supplier supplier = supplierService.getSupplierById(id);
        if (supplier== null){
            System.out.println(
                    "Supplier not found"
            );
            return;
        }
        supplierService.deleteSupplier(id);

    }

    public Supplier getSupplierById() {
        System.out.println("Enter supplier ID to delete :");
        int id = Integer.parseInt(scanner.nextLine());
        Supplier supplier = supplierService.getSupplierById(id);
        if (supplier== null){
            System.out.println(
                    "Supplier not found"
            );
            return null;
        }
        return supplierService.getSupplierById(id);
    }

    public ArrayList<Supplier> listSuppliers() {
        return supplierService.listSuppliers();
    }
}
