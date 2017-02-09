package com.team11.mutualfund.form;

import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.NOENOUGHCASH;
import static com.team11.mutualfund.utils.Constant.TOOLITTLEAMOUNT;

public class BuyFundForm {

    @NotNull(message = "fund ticker may not be null")
    @Size(min = 1, max = 5, message = "fund ticker length must between 1 and 5")
    @Pattern(regexp = "^[A-Z]*$*", message = "ticker must be Capitalized alphabet")
    private String fundTicker;

    @NotNull(message = "amount cannot be empty")
    private Double amount;

    private Double available;

    public Errors getValidationError() {
        Errors errors = new DirectFieldBindingResult(this, "buyFundForm");
        if (amount != null && amount < 0.01)
            errors.rejectValue("amount", "", TOOLITTLEAMOUNT);
        if (amount != null && available != null && amount > available)
            errors.rejectValue("amount", "", NOENOUGHCASH);
        return errors;
    }
    public String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public String getFundTicker() {
        return fundTicker;
    }

    public void setFundTicker(String fundTicker) {
        this.fundTicker = sanitize(fundTicker);
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAvailable() {
        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }
}
