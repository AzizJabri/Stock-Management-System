package services;

import interfaces.IStockMovementService;
import models.MovementType;
import models.Product;
import models.StockMovement;
import repositories.ProductRepository;
import repositories.StockMovementRepository;
import java.util.ArrayList;
import java.util.Date;

import static models.MovementType.*;

public class StockMovementService implements IStockMovementService {
    private static StockMovementService instance;
    private final StockMovementRepository stockMovementRepository;

    private final ProductRepository productRepository;

    private StockMovementService() {
        this.stockMovementRepository = StockMovementRepository.getInstance();
        this.productRepository = ProductRepository.getInstance();
    }

    public static StockMovementService getInstance() {
        if (instance == null) {
            instance = new StockMovementService();
        }
        return instance;
    }

    @Override
    public void addStockMovement(int productId, int quantity, Enum<MovementType> movementType, String reference) {
        try {
            Product product = productRepository.getById(productId);
            StockMovement movement = new StockMovement(productId, quantity, movementType, new Date(), reference);

            switch (movementType) {
                case INCOMING:
                    product.setQuantity_in_stock(product.getQuantity_in_stock() + quantity);
                    productRepository.update(product);
                    break;
                case OUTGOING:
                    if (product.getQuantity_in_stock() >= quantity) {
                        product.setQuantity_in_stock(product.getQuantity_in_stock() - quantity);
                        productRepository.update(product);
                    } else {
                        System.out.println("Insufficient stock for outgoing movement.");
                    }
                    break;
                case ADJUSTMENT:
                    product.setQuantity_in_stock(quantity);
                    productRepository.update(product);
                    break;
                default:
                    System.out.println("Invalid movement type.");
                    return;
            }
            stockMovementRepository.save(movement);
            
        } catch (Exception e) {
            System.out.println("Error adding stock movement: " + e.getMessage());
        }
    }

    @Override
    public void updateStockMovement(StockMovement stockMovement) {
        try {
            // Check if product exists
            if (productRepository.getById(stockMovement.getProduct_id()) == null) {
                System.out.println("Product not found");
                return;
            }
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