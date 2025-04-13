package controllers;

import models.Supplier;
import services.SupplierService;

import java.util.ArrayList;

public class SupplierController {
    private static SupplierController instance;
    private final SupplierService supplierService;

    private SupplierController() {
        if (instance == null) {
            instance = this;
        }
        this.supplierService = SupplierService.getInstance();
    }

    public static SupplierController getInstance() {
        if (instance == null) {
            instance = new SupplierController();
        }
        return instance;
    }

    public void addSupplier(String name, String phone, String address) {
        supplierService.addSupplier(name, phone, address);
    }

    public void updateSupplier(Supplier supplier) {
        supplierService.updateSupplier(supplier);
    }

    public void deleteSupplier(int id) {
        supplierService.deleteSupplier(id);
    }

    public Supplier getSupplierById(int id) {
        return supplierService.getSupplierById(id);
    }

    public ArrayList<Supplier> listSuppliers() {
        return supplierService.listSuppliers();
    }
}
