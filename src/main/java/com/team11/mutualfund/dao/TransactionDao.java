package com.team11.mutualfund.dao;

import com.team11.mutualfund.model.Transaction;
import com.team11.mutualfund.utils.TransactionType;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public class TransactionDao extends AbstractDao<Long, Transaction> {
    public void saveTransaction(Transaction transaction) {
        persist(transaction);
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> listAllOrderByDate() {
        Query query = getSession().createQuery(
                "select t from Transaction t"
        );
        return (List<Transaction>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> listPendingTransactionByType(TransactionType type) {
        Query query = getSession().createQuery(
                "select t from Transaction t where " +
                        "t.executeDate is null and " +
                        "t.type = :type"
        )
                .setParameter("type", type);
        return (List<Transaction>) query.list();
    }

    public List<Transaction> listPendingTransactionByTypeForUpdate(TransactionType type) {
        Query query = getSession().createQuery(
                "select t from Transaction t where " +
                        "t.executeDate is null and " +
                        "t.type = :type"
        )
                .setParameter("type", type)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE);
        return (List<Transaction>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> listPendingTransactionByCustomerId(long cid) {
        Query query = getSession().createQuery(
                "select t from Transaction t where " +
                        "t.executeDate is null and " +
                        "t.customer.id = :cid"
        )
                .setParameter("cid", cid);
        return (List<Transaction>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> listFinishTransactionByCustomerId(long cid) {
        Query query = getSession().createQuery(
                "select t from Transaction t where " +
                        "t.executeDate is not null and " +
                        "t.customer.id = :cid"
        )
                .setParameter("cid", cid);
        return (List<Transaction>) query.list();
    }
}
