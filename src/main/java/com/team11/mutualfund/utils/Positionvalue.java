package com.team11.mutualfund.utils;

import com.team11.mutualfund.model.Fund;

public class Positionvalue {

    private Fund fund;

    private Double shares;

    private Double price;


    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public Double getShares() {
        return shares;
    }

    public void setShares(Double shares) {
        this.shares = shares;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
