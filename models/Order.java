package models;

import java.util.Date;

public class Order {
    private int id;
    private int customer_id;
    private Date order_date;
    private String status;
    private double total_amount;

    public Order(int customer_id, Date order_date, String status, double total_amount) {
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.status = status;
        this.total_amount = total_amount;
    }

    public Order(int id, int customer_id, Date order_date, String status, double total_amount) {
        this(customer_id, order_date, status, total_amount);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public String getStatus() {
        return status;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", order_date=" + order_date +
                ", status='" + status + '\'' +
                ", total_amount=" + total_amount +
                '}';
    }
}