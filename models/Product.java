package models;

import java.util.Date;

public class Product {
    private int id;
    private int category_id;
    private String name;
    private String description;
    private float price;
    private int quantity_in_stock;
    private int supplier_id;
    private Date created_at;
    private Date updated_at;

    // Constructor without id (for creating new products)
    public Product(int category_id, String name, String description, float price,
                   int quantity_in_stock, int supplier_id, Date created_at, Date updated_at) {
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity_in_stock = quantity_in_stock;
        this.supplier_id = supplier_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Constructor with id (for existing products)
    public Product(int id, int category_id, String name, String description, float price,
                   int quantity_in_stock, int supplier_id, Date created_at, Date updated_at) {
        this(category_id, name, description, price, quantity_in_stock, supplier_id, created_at, updated_at);
        this.id = id;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}