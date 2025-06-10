package controllers;

import models.MovementType;
import models.StockMovement;
import services.ProductService;
import services.StockMovementService;
import java.util.ArrayList;
import java.util.Scanner;

public class StockMovementController {
    private static StockMovementController instance;
    private final StockMovementService stockMovementService;
    private final ProductController productController;
    private final ProductService productService;

    private final Scanner scanner = new Scanner(System.in);

    private StockMovementController() {
        this.stockMovementService = StockMovementService.getInstance();
        this.productController = ProductController.getInstance();
        this.productService = ProductService.getInstance();
    }

    public static StockMovementController getInstance() {
        if (instance == null) {
            instance = new StockMovementController();
        }
        return instance;
    }

    public void recordMovement() {
        productController.listProducts();
        if (productService.listProducts().isEmpty()) {
            System.out.println("No products available to record movement.");
            return;
        }
        try {
            System.out.println("Enter StockMovement productId: ");
            int productId = Integer.parseInt(scanner.nextLine());
            // Check if product exists
            if (productService.getProductById(productId) == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.println("Enter StockMovement quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter StockMovement movementType: ");
            System.out.println("1. INCOMING");
            System.out.println("2. OUTGOING");
            System.out.println("3. ADJUSTMENT");
            int movementTypeChoice = Integer.parseInt(scanner.nextLine());
            Enum<MovementType> movementType;
            switch (movementTypeChoice) {
                case 1 -> movementType = MovementType.INCOMING;
                case 2 -> movementType = MovementType.OUTGOING;
                case 3 -> movementType = MovementType.ADJUSTMENT;
                default -> {
                    System.out.println("Invalid choice. Defaulting to INGOING.");
                    movementType = MovementType.INCOMING;
                }
            }

            System.out.println("Enter StockMovement reference: ");
            String reference = scanner.nextLine();

            stockMovementService.addStockMovement(productId, quantity, movementType, reference);
            System.out.println("StockMovement recorded successfully.");
        } catch (Exception e) {
            System.out.println("Error while a adding product: " + e.getMessage());

        }

    }

    public void updateMovement() {
        StockMovement movement = getMovementDetails();
        if (movement == null) {
            System.out.println("StockMovement not found");
            return;
        }

        System.out.println("Leave input empty to keep the current value.");

        System.out.println("Current productId: " + movement.getProduct_id());
        System.out.print("Enter new productId: ");
        String productIdInput = scanner.nextLine();
        if (!productIdInput.isEmpty()) {
            int productId = Integer.parseInt(productIdInput);
            movement.setProduct_id(productId);
        }

        System.out.println("Current quantity: " + movement.getQuantity());
        System.out.print("Enter new quantity: ");
        String quantityInput = scanner.nextLine();
        if (!quantityInput.isEmpty()) {
            int quantity = Integer.parseInt(quantityInput);
            movement.setQuantity(quantity);
        }

        System.out.println("Current movementType: " + movement.getMovement_type());
        System.out.print("Enter new movementType: ");
        String movementType = scanner.nextLine();
        if (!movementType.isEmpty()) {
            movement.setMovement_type(MovementType.valueOf(movementType));
        }

        System.out.println("Current reference: " + movement.getReference());
        System.out.print("Enter new reference: ");
        String reference = scanner.nextLine();
        if (!reference.isEmpty()) {
            movement.setReference(reference);
        }

        stockMovementService.updateStockMovement(movement);
        System.out.println("Stock movement updated successfully.");
    }


    public void removeMovement() {
        StockMovement movement = getMovementDetails();
        if (movement == null) {
            System.out.println("StockMovement not found");
            return;
        }
        stockMovementService.deleteStockMovement(movement.getId());
        System.out.println("StockMovement deleted successfully.");
    }

    public StockMovement getMovementDetails() {
        System.out.println("Enter StockMovement ID:");
        int movementId = Integer.parseInt(scanner.nextLine());
        StockMovement movement = stockMovementService.getStockMovementById(movementId);
        if (movement == null) {
            System.out.println("StockMovement not found");
            return null;
        }
        return stockMovementService.getStockMovementById(movementId);

    }

    public void getAllMovements() {
        ArrayList<StockMovement> movements = stockMovementService.listAllStockMovements();
        if (movements.isEmpty()) {
            System.out.println("No stock movements found.");
        } else {
            for (StockMovement movement : movements) {
                System.out.println("ID: " + movement.getId());
                System.out.println("Product ID: " + movement.getProduct_id());
                System.out.println("Quantity: " + movement.getQuantity());
                System.out.println("Movement Type: " + movement.getMovement_type());
                System.out.println("Date: " + movement.getDate());
                System.out.println("Reference: " + movement.getReference());
                System.out.println("------------------------------");
            }
        }
    }
}