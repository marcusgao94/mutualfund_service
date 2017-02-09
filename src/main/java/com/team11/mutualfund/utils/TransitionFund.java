package com.team11.mutualfund.utils;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.team11.mutualfund.model.Fund;

public class TransitionFund {
    private Fund fund;
    private String lastPrice;
    
    @Min(0)
    private Double newPrice;

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }
}
