package models;

public class Customer extends User {
    private String address;
    private String email;

    public Customer(String fullName, int age, int phoneNumber, String address, String email) {
        super(fullName, age, phoneNumber);
        this.address = address;
        this.email = email;
    }

    // Getters and Setters
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
