package com.team11.mutualfund.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Position implements Serializable {

    @EmbeddedId
    private CustomerFund customerFund;

    @Column(nullable = false)
    private int shares;

    @MapsId("customerId")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User user;

    @MapsId("fundId")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fund_id", referencedColumnName = "id")
    private Fund fund;

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

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }


}
