package org.example.spotfy.Models;

public class User {

    private String email;
    private String password;
    private RoleUser role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}