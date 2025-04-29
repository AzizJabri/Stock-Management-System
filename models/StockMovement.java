package models;

import java.util.Date;

public class StockMovement {
    private int id;
    private int product_id;
    private int quantity;
    private Enum<MovementType> movement_type;
    private Date date;
    private String reference;

    public StockMovement(int product_id, int quantity, Enum<MovementType> movement_type, Date date, String reference) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.movement_type = movement_type;
        this.date = date;
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Enum<MovementType> getMovement_type() {
        return movement_type;
    }

    public Date getDate() {
        return date;
    }

    public String getReference() {
        return reference;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMovement_type(Enum<MovementType> movement_type) {
        this.movement_type = movement_type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}