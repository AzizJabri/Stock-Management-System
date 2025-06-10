package interfaces;

import models.OrderItem;
import java.util.ArrayList;

public interface IOrderItemService {
    void addOrderItem(int orderId, int productId, int quantity, double unitPrice);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int id);
    OrderItem getOrderItemById(int id);

    ArrayList<OrderItem> getOrderItemsByOrderId(int orderId);
    ArrayList<OrderItem> listAllOrderItems();
}