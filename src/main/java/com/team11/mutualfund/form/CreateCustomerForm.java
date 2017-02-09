package com.team11.mutualfund.form;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.*;

import static com.team11.mutualfund.utils.Constant.*;


public class CreateCustomerForm {

    @Size(min = 1, max = 20, message = USERNAMELENGTH)
    private String userName;

    @Size(min = 1, max = 20, message = PASSWORDLENGTH)
    private String password;

    @Size(min = 1, max = 20, message = FIRSTNAMELENGTH)
    private String firstName;
    
    @Size(min = 1, max = 20, message = LASTNAMELENGTH)
    private String lastName;
    
    @Size(min = 1, max = 50, message = ADDRESSLENGTH)
    private String address;

    @Size(min = 1, max = 20, message = CITYLENGTH)
    private String city;

    @Size(min = 1, max = 20, message = STATELENGTH)
    private String state;
    
    @Size(min = 5, max = 6, message = ZIPLENGTH)
    private String zip;

    public String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public Errors getValidationErrors() {
        Errors errors = new DirectFieldBindingResult(this, "customerRegisterForm");
        return errors;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = sanitize(userName);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = sanitize(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = sanitize(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = sanitize(lastName);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = sanitize(address);
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = sanitize(zip);
    }
}
