package com.team11.mutualfund.utils;


public class SessionUser {
    private Long id;
    private String userName;
    private String password;
    private String role;

    public SessionUser() {}
    public SessionUser(Long id, String userName, String role) {
        setId(id);
        setUserName(userName);
        setRole(role);
    }

    // getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
