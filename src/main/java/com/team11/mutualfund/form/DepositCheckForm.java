package com.team11.mutualfund.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.sanitize;

public class DepositCheckForm {

    @NotNull
    @Size(min = 1, max = 200)
    private String username;

    @NotNull
    @Size(min = 1, max = 60)
    private String cash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = sanitize(username);
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = sanitize(cash);
    }
}
