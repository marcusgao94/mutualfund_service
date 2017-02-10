package com.team11.mutualfund.response;

import com.team11.mutualfund.utils.Positionvalue;

import java.util.List;

public class ViewPortfolioResponse extends BasicResponse {
    private String cash;
    private List<Positionvalue> funds;

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public List<Positionvalue> getFunds() {
        return funds;
    }

    public void setFunds(List<Positionvalue> funds) {
        this.funds = funds;
    }
}
