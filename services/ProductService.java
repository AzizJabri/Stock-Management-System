package services;

import interfaces.IProductService;
import models.Product;
import repositories.CategoryRepository;
import repositories.ProductRepository;
import repositories.SupplierRepository;

import java.util.ArrayList;

public class ProductService implements IProductService {
    private static ProductService instance;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final SupplierRepository supplierRepository;

    private ProductService() {
        this.productRepository = ProductRepository.getInstance();
        this.categoryRepository = CategoryRepository.getInstance();
        this.supplierRepository = SupplierRepository.getInstance();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public void addProduct(int category_id, String name, String description, float price,
                           int quantity_in_stock, int supplier_id, java.util.Date created_at,
                           java.util.Date updated_at) {
        try {
            // Check if category and supplier exist
            if (categoryRepository.getById(category_id) == null) {
                System.out.println("Category not found");
                return;
            }
            if (supplierRepository.getById(supplier_id) == null) {
                System.out.println("Supplier not found");
                return;
            }
            Product product = new Product(category_id, name, description, price,
                    quantity_in_stock, supplier_id, created_at, updated_at);
            productRepository.save(product);
        } catch (Exception e) {
            System.out.println("Error saving Product: " + e.getMessage());
        }
    }

    public void updateProduct(Product product) {
        try {
            // Check if category and supplier exist
            if (categoryRepository.getById(product.getCategory_id()) == null) {
                System.out.println("Category not found");
                return;
            }
            if (supplierRepository.getById(product.getSupplier_id()) == null) {
                System.out.println("Supplier not found");
                return;
            }
            productRepository.update(product);
        } catch (Exception e) {
            System.out.println("Error updating Product: " + e.getMessage());
        }
    }

    public void deleteProduct(int id) {
        try {
            productRepository.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting Product: " + e.getMessage());
        }
    }

    public Product getProductById(int id) {
        try {
            return productRepository.getById(id);
        } catch (Exception e) {
            System.out.println("Error fetching Product: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Product> listProducts() {
        try {
            return productRepository.getAll();
        } catch (Exception e) {
            System.out.println("Error fetching products: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}