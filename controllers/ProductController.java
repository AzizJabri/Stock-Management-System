package controllers;

import models.Product;
import services.CategoryService;
import services.ProductService;
import services.SupplierService;

import java.util.Scanner;

public class ProductController {
    private static ProductController instance;
    private final ProductService productService;

    private final CategoryController categoryController;

    private final CategoryService categoryService;

    private final SupplierService supplierService;

    private final SupplierController supplierController;


    private final Scanner scanner = new Scanner(System.in);

    private ProductController() {

        this.productService = ProductService.getInstance();
        this.categoryController = CategoryController.getInstance();
        this.categoryService = CategoryService.getInstance();
        this.supplierService = SupplierService.getInstance();
        this.supplierController = SupplierController.getInstance();
    }

    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    public void addProduct() {
        categoryController.listCategories();
        try {
            System.out.println("Entrer Product categoryId: ");
            int categoryId = Integer.parseInt(scanner.nextLine());

            // Check if category exists
            if (categoryService.getCategoryById(categoryId) == null) {
                System.out.println("Category not found");
                return;
            }

            System.out.println("Entrer Product name: ");
            String name = scanner.nextLine();
            System.out.println("Entrer Product description: ");
            String description = scanner.nextLine();
            System.out.println("Entrer Product price: ");
            float price = Float.parseFloat(scanner.nextLine());
            System.out.println("Entrer Product quantityInStock: ");
            int quantityInStock = Integer.parseInt(scanner.nextLine());

            supplierController.listSuppliers();
            System.out.println("Entrer Product supplierId: ");
            int supplierId = Integer.parseInt(scanner.nextLine());
            // Check if supplier exists
            if (supplierService.getSupplierById(supplierId) == null) {
                System.out.println("Supplier not found");
                return;
            }

            productService.addProduct(categoryId, name, description, price,
                    quantityInStock, supplierId);

            System.out.println("Product added successfully!");
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

        System.out.println("Leave input empty to keep current value.");

        System.out.println("Current categoryId: " + product.getCategory_id());
        System.out.print("Enter new categoryId: ");
        String categoryInput = scanner.nextLine();
        if (!categoryInput.isEmpty()) {
            int newCategoryId = Integer.parseInt(categoryInput);
            if (categoryService.getCategoryById(newCategoryId) == null) {
                System.out.println("Category not found");
                return;
            }
            product.setCategory_id(newCategoryId);
        }

        System.out.println("Current name: " + product.getName());
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) product.setName(name);

        System.out.println("Current description: " + product.getDescription());
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) product.setDescription(description);

        System.out.println("Current price: " + product.getPrice());
        System.out.print("Enter new price: ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) product.setPrice(Float.parseFloat(priceInput));

        System.out.println("Current quantity in stock: " + product.getQuantity_in_stock());
        System.out.print("Enter new quantity: ");
        String quantityInput = scanner.nextLine();
        if (!quantityInput.isEmpty()) product.setQuantity_in_stock(Integer.parseInt(quantityInput));

        supplierController.listSuppliers();
        System.out.println("Current supplierId: " + product.getSupplier_id());
        System.out.print("Enter new supplierId: ");
        String supplierInput = scanner.nextLine();
        if (!supplierInput.isEmpty()) {
            int newSupplierId = Integer.parseInt(supplierInput);
            if (supplierService.getSupplierById(newSupplierId) == null) {
                System.out.println("Supplier not found");
                return;
            }
            product.setSupplier_id(newSupplierId);
        }

        productService.updateProduct(product);
        System.out.println("Product updated successfully.");
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
            System.out.println("Product not found");
            return null;
        }
        return product;
    }

    public void listProducts() {
        try {
            for (Product product : productService.listProducts()) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("Category ID: " + product.getCategory_id());
                System.out.println("Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity in Stock: " + product.getQuantity_in_stock());
                System.out.println("Supplier ID: " + product.getSupplier_id());
                System.out.println("Created At: " + product.getCreated_at());
                System.out.println("Updated At: " + product.getUpdated_at());
                System.out.println("-------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error while listing products: " + e.getMessage());
        }
    }
}