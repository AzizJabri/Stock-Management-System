package models;

public class OrderItem {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double unit_price;

    public OrderItem(int order_id, int product_id, int quantity, double unit_price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public OrderItem(int id, int order_id, int product_id, int quantity, double unit_price) {
        this(order_id, product_id, quantity, unit_price);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                '}';
    }
}