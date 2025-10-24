package main.java.acko.Models;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private UserType userType;

    public User(String userId, String name, String email, String phoneNumber, UserType userType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public boolean isValidEmail() {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public void updateProfile(String name, String phone) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (phone != null && !phone.trim().isEmpty()) {
            this.phoneNumber = phone;
        }
    }

    public enum UserType {
        CUSTOMER,
        ADMIN
    }
}