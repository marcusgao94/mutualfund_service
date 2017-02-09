package com.team11.mutualfund.form;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.INCONSISTENTPASSWORD;

public class CreateFundForm {

    @NotNull(message = "fund name may not be null")
    @NotEmpty(message = "fund name may not be empty")
    private String fundName;

    @NotNull(message = "fund ticker may not be null")
    @Size(min = 1, max = 5, message = "ticker length must between 1 to 5")
    @Pattern(regexp = "^[A-Z]*$*", message = "ticker must be Capitalized alphabet")
    private String fundTicker;

    public String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = sanitize(fundName);
    }

    public String getFundTicker() {
        return fundTicker;
    }

    public void setFundTicker(String fundTicker) {
        this.fundTicker = sanitize(fundTicker);
    }
}
