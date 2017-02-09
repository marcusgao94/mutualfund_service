package com.team11.mutualfund.model;

import com.team11.mutualfund.form.CreateCustomerForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.team11.mutualfund.utils.Constant.*;

@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue
	private long id;

	@Size(min = 1, max = 20, message = USERNAMELENGTH)
	@Column(nullable = false, unique = true)
	private String username;


	@Size(min = 1, max = 20, message = PASSWORDLENGTH)
	@Column(nullable = false)
	private String password;

	@Size(min = 1, max = 20, message = FIRSTNAMELENGTH)
    @Column(nullable = false)
	private String fname;

	@Size(min = 1, max = 20, message = LASTNAMELENGTH)
	@Column(nullable = false)
	private String lname;

	@Size(min = 1, max = 30, message = EMAILLENGTH)
	@Column(nullable = false)
	private String email;

	@Size(min = 1, max = 50, message = ADDRESSLENGTH)
	@Column(nullable = false)
	private String address;

	@Size(min = 1, max = 20, message = CITYLENGTH)
	@Column(nullable = false)
	private String city;

	@Size(min = 2, max = 20, message = STATELENGTH)
	@Column(nullable = false)
	private String state;

	@Size(min = 5, max = 6, message = ZIPLENGTH)
	@Column(nullable = false)
	private String zip;

	@NotNull
	@Column(nullable=false, scale = 2)
	private Double cash = 0d;


	// getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}
}
