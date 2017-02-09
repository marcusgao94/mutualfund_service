package com.team11.mutualfund.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerFund implements Serializable {
    private long customerId;
    private long fundId;

    public CustomerFund() {}
    public CustomerFund(long cid, long fid) {
        customerId = cid;
        fundId = fid;
    }

    @Override
    public boolean equals(Object o) {
        CustomerFund cf = (CustomerFund) o;
        return customerId == cf.getCustomerId() && fundId == cf.getFundId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, fundId);
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getFundId() {
        return fundId;
    }

    public void setFundId(long fundId) {
        this.fundId = fundId;
    }
}
