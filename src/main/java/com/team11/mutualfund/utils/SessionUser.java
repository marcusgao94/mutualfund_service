package com.team11.mutualfund.utils;


public class SessionUser {
    private Long id;
    private String username;
    private String role;

    public SessionUser() {}
    public SessionUser(Long id, String username, String role) {
        setId(id);
        setUsername(username);
        setRole(role);
    }

    // getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
