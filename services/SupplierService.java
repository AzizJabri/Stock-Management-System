package services;

import interfaces.ISupplierService;
import models.Supplier;
import repositories.SupplierRepository;
import java.util.ArrayList;

public class SupplierService implements ISupplierService {
    private static SupplierService instance;
    private final SupplierRepository supplierRepository;


    private SupplierService() {
        this.supplierRepository = SupplierRepository.getInstance();
    }

    public static SupplierService getInstance() {
        if (instance == null) {
            instance = new SupplierService();
        }
        return instance;
    }

    public void addSupplier(String name, String phone, String address) {
        try {
            Supplier S =new Supplier(name, phone, address);
            supplierRepository.save(S);
        } catch (Exception e) {
            System.out.println("Error saving Supplier: " + e.getMessage());
        }
    }

    public void updateSupplier(Supplier supplier) {
        try {
            supplierRepository.update(supplier);
        } catch (Exception e) {
            System.out.println("Error updating Supplier: " + e.getMessage());
        }
    }

    public void deleteSupplier(int id) {
        try {
            supplierRepository.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting Supplier: " + e.getMessage());
        }
    }

    public Supplier getSupplierById(int id) {
        try {
            return supplierRepository.getById(id);
        } catch (Exception e) {
            System.out.println("Error fetching Supplier: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Supplier> listSuppliers() {
        try {
            return supplierRepository.getAll();
        } catch (Exception e) {
            System.out.println("Error fetching categories: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
