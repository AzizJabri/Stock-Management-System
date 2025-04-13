package interfaces;

import models.StockMovement;
import java.util.ArrayList;

public interface IStockMovementService {
    void addStockMovement(int productId, int quantity, String movementType, String reference);
    void updateStockMovement(StockMovement stockMovement);
    void deleteStockMovement(int id);
    StockMovement getStockMovementById(int id);
    ArrayList<StockMovement> listAllStockMovements();
}