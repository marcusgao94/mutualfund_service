package com.team11.mutualfund.form;

import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.team11.mutualfund.utils.Constant.NOENOUGHCASH;
import static com.team11.mutualfund.utils.Constant.TOOLITTLEAMOUNT;

public class RequestCheckForm {

//    @NotNull(message = "customer id cannot be null")
//    private Long customerId;
	@NotNull(message = "customer userName cannot be null")
	private String userName;

    @NotNull(message = "amount cannot be null")
    private Double amount;

    private Double available;

    public Errors getValidationErrors() {
        Errors errors = new DirectFieldBindingResult(this, "requestCheckForm");
        if (getAmount() < 0.01)
            errors.rejectValue("amount", "0", TOOLITTLEAMOUNT);
        if (getAmount() > getAvailable())
            errors.rejectValue("amount", "1", NOENOUGHCASH);
        return errors;
    }
    public String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = sanitize(userName);
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
