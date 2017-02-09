package com.team11.mutualfund.form;

import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.NOENOUGHCASH;
import static com.team11.mutualfund.utils.Constant.TOOLITTLEAMOUNT;

public class ResearchFundForm {

    @NotNull(message = "fund ticker cannot be null")
    @Size(min = 1, max = 5, message = "fund ticker length must between 1 to 5")
    private String ticker;

    //private Double available;

    /*
    public Errors getValidationErrors() {
        Errors errors = new DirectFieldBindingResult(this, "researchFundForm");
        if (getAmount() < 0.01)
            errors.rejectValue("amount", "0", TOOLITTLEAMOUNT);
        if (getAmount() > getAvailable())
            errors.rejectValue("amount", "1", NOENOUGHCASH);
        return errors;
    } */
    public String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = sanitize(ticker);
    }

}
