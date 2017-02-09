package com.team11.mutualfund.service;

import com.team11.mutualfund.dao.*;
import com.team11.mutualfund.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.util.List;

import static com.team11.mutualfund.utils.Constant.*;
import static com.team11.mutualfund.utils.TransactionType.*;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FundDao fundDao;

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private FundPriceHistoryDao fundPriceHistoryDao;

    public List<Transaction> listPendingTransactionByCustomerId(long cid) {
        return transactionDao.listPendingTransactionByCustomerId(cid);
    }

    public List<Transaction> listFinishTransactionByCustomerId(long cid) {
        return transactionDao.listFinishTransactionByCustomerId(cid);
    }

    public void buyFund(long cid, String ticker, double amount) throws RollbackException {
        User user = userDao.findByIdForUpdate(cid);
        Fund fund = fundDao.findByTicker(ticker);
        if (user == null)
            throw new RollbackException("user id " + String.valueOf(cid) + " does not exist");
        if (fund == null)
            throw new RollbackException("fund ticker " + String.valueOf(ticker) + " does not exist");
        /*
        if (user.getCash() < user.getPendingCashDecrease() + amount)
            throw new RollbackException(NOENOUGHCASH);

        user.setPendingCashDecrease(user.getPendingCashDecrease() + amount);
        */
        transactionDao.saveTransaction(new Transaction(user, fund, BUYFUND, null, amount));
    }

    public void sellFund(long cid, String ticker, double shares) throws RollbackException {
        User user = userDao.findByIdForUpdate(cid);
        Fund fund = fundDao.findByTicker(ticker);
        if (user == null)
            throw new RollbackException("user id " + String.valueOf(cid) + " does not exist");
        if (fund == null || !fund.getTicker().equals(ticker))
            throw new RollbackException("fund ticker " + String.valueOf(ticker) + " does not exist");
        CustomerFund cf = new CustomerFund();
        cf.setCustomerId(cid);
        cf.setFundId(fund.getId());
        Position position = positionDao.findByCustomerFundForUpdate(cf);
        if (position == null)
            throw new RollbackException("user does not have fund " +
                    String.valueOf(fund.getTicker()));
        if (position.getShares() < position.getPendingShareDecrease() + shares)
            throw new RollbackException(NOENOUGHSHARE);
        position.setPendingShareDecrease(position.getPendingShareDecrease() + shares);
        transactionDao.saveTransaction(new Transaction(user, fund, SELLFUND, shares, null));
    }

    public void requestCheck(String userName, double amount) throws RollbackException {
        User user = userDao.findByUserNameForUpdate(userName);
        if (user == null)
            throw new RollbackException("user username " + String.valueOf(userName) + " does not exist");

        // check enough cash
        /*
        if (user.getCash() < user.getPendingCashDecrease() + amount)
            throw new RollbackException(NOENOUGHCASH);

        user.setPendingCashDecrease(user.getPendingCashDecrease() + amount);
        transactionDao.saveTransaction(new Transaction(user, null, REQUESTCHECK, null, amount));
        */
    }

    public void depositCheck(String userName, double amount) throws RollbackException {
        User user = userDao.findByUserName(userName);
        if (user == null)
            throw new RollbackException("user id " + String.valueOf(userName) + " does not exist");
        transactionDao.saveTransaction(new Transaction(user, null, DEPOSITCHECK, null, amount));
    }









}
