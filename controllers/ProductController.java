package controllers;

import models.Product;
import models.StockMovement;
import models.Supplier;
import services.ProductService;
import services.SupplierService;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductController {
    private static ProductController instance;
    private final ProductService productService;


    private final Scanner scanner = new Scanner(System.in);

    private ProductController() {

        this.productService = ProductService.getInstance();
    }

    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    public void addProduct() {
        try {
            System.out.println("Entrer Product id: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Entrer Product categoryId: ");
            int categoryId = Integer.parseInt(scanner.nextLine());
            System.out.println("Entrer Product name: ");
            String name = scanner.nextLine();
            System.out.println("Entrer Product description: ");
            String description = scanner.nextLine();
            System.out.println("Entrer Product price: ");
            float price = Float.parseFloat(scanner.nextLine());
            System.out.println("Entrer Product quantityInStock: ");
            int quantityInStock = Integer.parseInt(scanner.nextLine());
            System.out.println("Entrer Product supplierId: ");
            int supplierId = Integer.parseInt(scanner.nextLine());


            productService.addProduct(categoryId, name, description, price,
                    quantityInStock, supplierId);


        } catch (Exception e) {
            System.out.println("Error while a adding product: " + e.getMessage());

        }


    }

    public void updateProduct() {

        Product product = getProductById();
        if (product == null) {
            System.out.println("Product not found");
            return;
        }
        System.out.println("Entrer Product id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Entrer Product categoryId: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        System.out.println("Entrer Product name: ");
        String name = scanner.nextLine();
        System.out.println("Entrer Product description: ");
        String description = scanner.nextLine();
        System.out.println("Entrer Product price: ");
        float price = Float.parseFloat(scanner.nextLine());
        System.out.println("Entrer Product quantityInStock: ");
        int quantityInStock = Integer.parseInt(scanner.nextLine());
        System.out.println("Entrer Product supplierId: ");
        int supplierId = Integer.parseInt(scanner.nextLine());

        productService.updateProduct(product);
    }

    public void deleteProduct() {
        Product product = getProductById();
        if (product == null) {
            System.out.println("Product not found");
            return;
        }
        productService.deleteProduct(product.getId());
    }

    public Product getProductById() {
        System.out.println("Enter Product ID :");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println("StockMovement not found");
            return null;
        }
        return product;
    }

    public ArrayList<Product> listProducts() {
        return productService.listProducts();
    }
}