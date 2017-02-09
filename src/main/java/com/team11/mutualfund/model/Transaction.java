package com.team11.mutualfund.model;

import com.team11.mutualfund.utils.TransactionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn
    private Fund fund;
    

    @Column(nullable = true)
    private LocalDate executeDate;

    @Enumerated
    private TransactionType type;

    @Column(scale = 3)
    private Double shares;

    @Column(scale = 2)
    private Double amount;

    @Column(scale = 2)
    private Double price;

    public Transaction() {}
    public Transaction(User user, Fund fund, TransactionType type, Double shares, Double amount) {
        setUser(user);
        setFund(fund);
        setType(type);
        setShares(shares);
        setAmount(amount);
        setPrice(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(LocalDate executeDate) {
        this.executeDate = executeDate;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getShares() {
        return shares;
    }

    public void setShares(Double shares) {
        this.shares = shares;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
