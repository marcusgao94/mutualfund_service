package com.team11.mutualfund.form;

import com.team11.mutualfund.model.Fund;
import com.team11.mutualfund.model.FundPriceHistory;
import com.team11.mutualfund.utils.TransitionFund;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Constraint;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransitionForm {

    private String lastDate;

    @NotNull
    @Size(min = 10, max = 10, message = "new date must be the form MM/dd/yyyy")
    private String newDate;

    private List<TransitionFund> fundList;

    public String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public Errors getValidationErrors() {
        Errors errors = new DirectFieldBindingResult(this, "transitionForm");
        for (int i = 0; i < fundList.size(); i++) {
            TransitionFund tf = fundList.get(i);
            if (tf.getNewPrice() == null)
                errors.rejectValue("fundList[" + i + "].newPrice", "", "new price of every fund must be filled");
            else if (tf.getNewPrice() < 0.01)
                errors.rejectValue("fundList[" + i + "].newPrice", "", "new price must >= 0.01");
        }
        return errors;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
    
    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = sanitize(newDate);
    }

    public List<TransitionFund> getFundList() {
        return fundList;
    }

    public void setFundList(List<TransitionFund> fundList) {
        this.fundList = fundList;
    }
}
