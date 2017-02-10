package com.team11.mutualfund.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.sanitize;

public class RequestCheckForm {

    @NotNull
    @Size(min = 1, max = 20)
    private String cashValue;

    public String getCashValue() {
        return cashValue;
    }

    public void setCashValue(String cashValue) {
        this.cashValue = sanitize(cashValue);
    }
}
