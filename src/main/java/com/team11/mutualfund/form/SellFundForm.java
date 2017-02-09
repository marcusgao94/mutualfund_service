package com.team11.mutualfund.form;

import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.TOOLITTLEAMOUNT;
import static com.team11.mutualfund.utils.Constant.TOOLITTLESHARE;

public class SellFundForm {

    @NotNull
    @Size(min = 1, max = 5, message = "fund ticker length must between 1 and 5")
    private String fundTicker;

    @NotNull(message = "share cannot be empty")
    private Double share;

    public Errors getValidationError() {
        Errors errors = new DirectFieldBindingResult(this, "sellFundForm");
        if (share != null && share < 0.001) {
            errors.rejectValue("share", "0", TOOLITTLESHARE);
        }
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

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }
}
