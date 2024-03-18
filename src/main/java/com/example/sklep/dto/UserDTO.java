package com.example.sklep.dto;


public class UserDTO {

    private String username;

    private String role;

    private String enabled;

    public UserDTO() {
    }

    public UserDTO(String username, String role, String enabled) {
        this.username = username;
        this.role = role;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
