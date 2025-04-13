package controllers;

import models.StockMovement;
import services.StockMovementService;
import java.util.ArrayList;

public class StockMovementController {
    private static StockMovementController instance;
    private final StockMovementService stockMovementService;

    private StockMovementController() {
        this.stockMovementService = StockMovementService.getInstance();
    }

    public static StockMovementController getInstance() {
        if (instance == null) {
            instance = new StockMovementController();
        }
        return instance;
    }

    public void recordMovement(int productId, int quantity, String movementType, String reference) {
        stockMovementService.addStockMovement(productId, quantity, movementType, reference);
    }

    public void updateMovement(StockMovement movement) {
        stockMovementService.updateStockMovement(movement);
    }

    public void removeMovement(int movementId) {
        stockMovementService.deleteStockMovement(movementId);
    }

    public StockMovement getMovementDetails(int movementId) {
        return stockMovementService.getStockMovementById(movementId);
    }

    public ArrayList<StockMovement> getAllMovements() {
        return stockMovementService.listAllStockMovements();
    }
}