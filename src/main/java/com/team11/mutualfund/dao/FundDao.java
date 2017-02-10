package com.team11.mutualfund.dao;

import com.team11.mutualfund.model.Fund;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FundDao extends AbstractDao<Long, Fund> {
    public void saveFund(Fund fund) {
        persist(fund);
    }

    public Fund findById(long id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public Fund findBySymbol(String symbol) {
        Query query = getSession().createQuery(
                "select f from Fund f where f.symbol = :symbol"
        )
                .setParameter("symbol", symbol);
        return (Fund) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Fund> listFund() {
        Query query = getSession().createQuery(
                "select f from Fund f"
        );
        return (List<Fund>) query.list();
    }
    
}
