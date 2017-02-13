package com.team11.mutualfund.form;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.sanitize;


public class SellFundForm {

    @NotNull
    @Size(min = 1, max = 5)
    @Pattern(regexp = "^[A-Z]*$*", message = "symbol must be Capitalized alphabet")
    private String symbol;

    @NotNull
    @Size(min = 1, max = 10)
    private String numShares;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = sanitize(symbol);
    }

    public String getNumShares() {
        return numShares;
    }

    public void setNumShares(String numShares) {
        this.numShares = sanitize(numShares);
    }
}
