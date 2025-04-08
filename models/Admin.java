package models;

public class Admin extends User{
    private String username;
    private String password;

    public Admin(String fullName, int age, int phoneNumber, String username, String password) {
        super(fullName, age, phoneNumber);
        this.username = username;
        this.password = password;
    }

    public Admin(int id, String fullName, int age, int phoneNumber, String username, String password) {
        super(id, fullName, age, phoneNumber);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
