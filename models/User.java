package models;

public class User {
    private int id;
    private String fullName;
    private int age;
    private int phoneNumber;

    public User(int id, String fullName, int age, int phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
    public User(String fullName, int age, int phoneNumber) {
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

}
