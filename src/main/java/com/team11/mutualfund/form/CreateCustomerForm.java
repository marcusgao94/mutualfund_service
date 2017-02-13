package com.team11.mutualfund.form;

import javafx.scene.SceneAntialiasing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.team11.mutualfund.utils.Constant.*;

public class CreateCustomerForm {

    @NotNull
    @Size(min = 1, max = 200)
    private String username;

    @NotNull
    @Size(min = 1, max = 200)
    private String password;

    @NotNull
    @Size(min = 1, max = 200)
    private String fname;

    @NotNull
    @Size(min = 1, max = 200)
    private String lname;

    @NotNull
    @Size(min = 1, max = 200)
    private String email;

    @NotNull
    @Size(min = 1, max = 200)
    private String address;

    @NotNull
    @Size(min = 1, max = 200)
    private String city;

    @NotNull
    @Size(min = 1, max = 200)
    private String state;

    @NotNull
    @Size(min = 1, max = 200)
    private String zip;

    @NotNull
    @Size(min = 1, max = 60)
    private String cash;

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = sanitize(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = sanitize(password);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = sanitize(fname);
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = sanitize(lname);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = sanitize(email);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = sanitize(address);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = sanitize(city);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = sanitize(state);
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = sanitize(zip);
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = sanitize(cash);
    }
}
