package com.team11.mutualfund.model;

import java.io.Serializable;

import javax.persistence.*;

import com.team11.mutualfund.form.CreateFundForm;

@Entity
public class Fund implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String symbol;

    @Column(nullable = false, columnDefinition = "decimal(60, 2)")
    private double price;

    public Fund() {}
    public Fund(CreateFundForm cff) {
        setName(cff.getName());
        setSymbol(cff.getSymbol());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
