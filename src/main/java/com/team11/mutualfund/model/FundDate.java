package com.team11.mutualfund.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class FundDate implements Serializable {

    private Long fundId;

    private LocalDate date;

    public FundDate() {
    }

    public FundDate(long fid, LocalDate d) {
        fundId = fid;
        date = d;
    }

    @Override
    public boolean equals(Object o) {
        FundDate fdp = (FundDate) o;
        double delta = 1e-10;
        return fundId.equals(fdp.getFundId()) && date.equals(fdp.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fundId, date);
    }

    public Long getFundId() {
        return fundId;
    }

    public void setFundId(Long fundId) {
        this.fundId = fundId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
