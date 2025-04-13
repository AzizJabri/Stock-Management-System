package services;

import models.Product;
import repositories.ProductRepository;

import java.util.ArrayList;

public class ProductService {
    private static ProductService instance;
    private final ProductRepository productRepository;

    private ProductService() {
        this.productRepository = ProductRepository.getInstance();
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
            Product product = new Product(category_id, name, description, price,
                    quantity_in_stock, supplier_id, created_at, updated_at);
            productRepository.save(product);
        } catch (Exception e) {
            System.out.println("Error saving Product: " + e.getMessage());
        }
    }

    public void updateProduct(Product product) {
        try {
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