package com.team11.mutualfund.form;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.INCONSISTENTPASSWORD;
import static com.team11.mutualfund.utils.Constant.sanitize;

public class CreateFundForm {

    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 1, max = 5)
    @Pattern(regexp = "^[A-Z]*$*", message = "symbol must be Capitalized alphabet")
    private String symbol;

    @Size(min = 1, max = 20)
    private String initial_value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = sanitize(name);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = sanitize(symbol);
    }

    public String getInitial_value() {
        return initial_value;
    }

    public void setInitial_value(String initial_value) {
        this.initial_value = sanitize(initial_value);
    }
}
