package com.team11.mutualfund.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.team11.mutualfund.utils.Constant.*;

public class CreateCustomerForm {

    @Size(min = 1, max = 20)
    private String username;

    @Size(min = 1, max = 20)
    private String password;

    @Size(min = 1, max = 20)
    private String fname;

    @Size(min = 1, max = 20)
    private String lname;

    @Size(min = 1, max = 30)
    private String email;

    @Size(min = 1, max = 50)
    private String address;

    @Size(min = 1, max = 20)
    private String city;

    @Size(min = 2, max = 20)
    private String state;

    @Size(min = 5, max = 6)
    private String zip;

    @Size(min = 1, max = 20)
    private String cash;

    // getters and setters
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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }
}
