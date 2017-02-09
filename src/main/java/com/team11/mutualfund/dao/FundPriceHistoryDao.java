package com.team11.mutualfund.dao;

import com.team11.mutualfund.model.FundDate;
import com.team11.mutualfund.model.FundPriceHistory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FundPriceHistoryDao extends AbstractDao<Long, FundPriceHistory> {
    public void save(FundPriceHistory fundPriceHistory) {
        persist(fundPriceHistory);
    }

    @SuppressWarnings("unchecked")
    public FundPriceHistory findByFundDate(FundDate fundDate) {
        Query query = getSession().createQuery(
                "select fph from FundPriceHistory fph where " +
                        "fph.fundDate = :fd"
        )
                .setParameter("fd", fundDate);
        return (FundPriceHistory) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<FundPriceHistory> listByFundId(long fid) {
        Query query = getSession().createQuery(
                "select fph from FundPriceHistory fph where " +
                        "fph.fund.id = :fid " +
                        "order by fundDate.date desc"
        )
                .setParameter("fid", fid);
        return (List<FundPriceHistory>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<FundPriceHistory> listByFundTicker(String ft) {
        Query query = getSession().createQuery(
                "select fph from FundPriceHistory fph where " +
                        "fph.fund.ticker = :ft"
        )
                .setParameter("ft", ft);
        return (List<FundPriceHistory>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<FundPriceHistory> listAllOrderByDate() {
        Query query = getSession().createQuery(
                "select fph from FundPriceHistory fph order by fundDate.date desc"
        );
        return (List<FundPriceHistory>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<FundPriceHistory> listAllOrderByDateForUpdate() {
        Query query = getSession().createQuery(
                "select fph from FundPriceHistory fph"
        );
        return (List<FundPriceHistory>) query.list();
    }


}
