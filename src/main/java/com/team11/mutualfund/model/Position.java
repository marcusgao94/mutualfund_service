package com.team11.mutualfund.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Position implements Serializable {

    @EmbeddedId
    private CustomerFund customerFund;

    @Column(scale = 3)
    private double shares;

    @MapsId("customerId")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User user;

    @MapsId("fundId")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fund_id", referencedColumnName = "id")
    private Fund fund;

    @Column(scale = 3)
    private Double pendingShareDecrease = 0d;

    public CustomerFund getCustomerFund() {
        return customerFund;
    }

    public void setCustomerFund(CustomerFund customerFund) {
        this.customerFund = customerFund;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public double getShares() {
        return shares;
    }

    public void setShares(double shares) {
        this.shares = shares;
    }

    public Double getPendingShareDecrease() {
        return pendingShareDecrease;
    }

    public void setPendingShareDecrease(Double pendingShareDecrease) {
        this.pendingShareDecrease = pendingShareDecrease;
    }
}
