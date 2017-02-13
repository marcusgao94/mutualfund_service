package com.team11.mutualfund.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.sanitize;

public class BuyFundForm {

    @NotNull
    @Size(min = 1, max = 5)
    @Pattern(regexp = "^[A-Z]*$*", message = "symbol must be Capitalized alphabet")
    private String symbol;

    @NotNull
    @Size(min = 1, max = 60)
    private String cashValue;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = sanitize(symbol);
    }

    public String getCashValue() {
        return cashValue;
    }

    public void setCashValue(String cashValue) {
        this.cashValue = sanitize(cashValue);
    }
}
