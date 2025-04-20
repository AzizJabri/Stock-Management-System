package controllers;

import models.StockMovement;
import services.StockMovementService;
import java.util.ArrayList;
import java.util.Scanner;

public class StockMovementController {
    private static StockMovementController instance;
    private final StockMovementService stockMovementService;

    private final Scanner scanner = new Scanner(System.in);

    private StockMovementController() {
        this.stockMovementService = StockMovementService.getInstance();
    }

    public static StockMovementController getInstance() {
        if (instance == null) {
            instance = new StockMovementController();
        }
        return instance;
    }

    public void recordMovement() {
        try {
            System.out.println("Entrer StockMovement productId: ");
            int productId = Integer.parseInt(scanner.nextLine());
            System.out.println("Entrer StockMovement quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Entrer StockMovement movementType: ");
            String movementType = scanner.nextLine();
            System.out.println("Entrer StockMovement reference: ");
            String reference = scanner.nextLine();

            stockMovementService.addStockMovement(productId, quantity, movementType, reference);

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
        System.out.println("Entrer StockMovement productId: ");
        int productId = Integer.parseInt(scanner.nextLine());
        System.out.println("Entrer StockMovement quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Entrer StockMovement movementType: ");
        String movementType = scanner.nextLine();
        System.out.println("Entrer StockMovement reference: ");
        String reference = scanner.nextLine();
        stockMovementService.updateStockMovement(movement);
    }

    public void removeMovement() {
        StockMovement movement = getMovementDetails();
        if (movement == null) {
            System.out.println("StockMovement not found");
            return;
        }
        stockMovementService.deleteStockMovement(movement.getId());
    }

    public StockMovement getMovementDetails() {
        System.out.println("Enter StockMovement ID to delete :");
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