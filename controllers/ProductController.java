package controllers;

import models.Product;
import models.Supplier;
import services.ProductService;
import services.SupplierService;

import java.util.ArrayList;

public class ProductController {
    private static ProductController instance;
    private final ProductService productService;

    private ProductController() {
        if (instance == null) {
            instance = this;
        }
        this.productService = ProductService.getInstance();
    }

    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    public void addProduct(int categoryId, String name, String description,
                           float price, int quantityInStock, int supplierId,  java.util.Date created_at, java.util.Date updated_at) {

        productService.addProduct(categoryId, name, description, price,
                quantityInStock, supplierId, created_at, updated_at);
    }

    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productService.deleteProduct(id);
    }

    public Product getProductById(int id) {
        return productService.getProductById(id);
    }

    public ArrayList<Product> listProducts() {
        return productService.listProducts();
    }
}