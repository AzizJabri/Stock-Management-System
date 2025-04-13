package services;

import interfaces.IStockMovementService;
import models.StockMovement;
import repositories.StockMovementRepository;
import java.util.ArrayList;
import java.util.Date;

public class StockMovementService implements IStockMovementService {
    private static StockMovementService instance;
    private final StockMovementRepository stockMovementRepository;

    private StockMovementService() {
        this.stockMovementRepository = StockMovementRepository.getInstance();
    }

    public static StockMovementService getInstance() {
        if (instance == null) {
            instance = new StockMovementService();
        }
        return instance;
    }

    @Override
    public void addStockMovement(int productId, int quantity, String movementType, String reference) {
        try {
            StockMovement movement = new StockMovement(productId, quantity, movementType, new Date(), reference);
            stockMovementRepository.save(movement);
        } catch (Exception e) {
            System.out.println("Error adding stock movement: " + e.getMessage());
        }
    }

    @Override
    public void updateStockMovement(StockMovement stockMovement) {
        try {
            stockMovementRepository.update(stockMovement);
        } catch (Exception e) {
            System.out.println("Error updating stock movement: " + e.getMessage());
        }
    }

    @Override
    public void deleteStockMovement(int id) {
        try {
            stockMovementRepository.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting stock movement: " + e.getMessage());
        }
    }

    @Override
    public StockMovement getStockMovementById(int id) {
        try {
            return stockMovementRepository.getById(id);
        } catch (Exception e) {
            System.out.println("Error getting stock movement: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<StockMovement> listAllStockMovements() {
        try {
            return stockMovementRepository.getAll();
        } catch (Exception e) {
            System.out.println("Error listing stock movements: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}