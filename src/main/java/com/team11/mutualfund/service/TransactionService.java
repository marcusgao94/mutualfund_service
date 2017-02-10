package com.team11.mutualfund.service;

import com.team11.mutualfund.dao.*;
import com.team11.mutualfund.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;

import static com.team11.mutualfund.utils.Constant.*;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FundDao fundDao;

    @Autowired
    private PositionDao positionDao;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void buyFund(long uid, String symbol, double amount) throws RollbackException {
        User user = userDao.findById(uid);
        Fund fund = fundDao.findBySymbolForUpdate(symbol);
        if (user == null || fund == null)
            throw new RollbackException();
        /*
        if (user.getCash() < user.getPendingCashDecrease() + amount)
            throw new RollbackException(NOENOUGHCASH);

        user.setPendingCashDecrease(user.getPendingCashDecrease() + amount);
        */
    }

    public void sellFund(long cid, String symbol, double shares) throws RollbackException {
        User user = userDao.findByIdForUpdate(cid);
        Fund fund = fundDao.findBySymbol(symbol);
        if (user == null)
            throw new RollbackException("user id " + String.valueOf(cid) + " does not exist");
        if (fund == null || !fund.getSymbol().equals(symbol))
            throw new RollbackException("fund symbol " + String.valueOf(symbol) + " does not exist");
        CustomerFund cf = new CustomerFund();
        cf.setCustomerId(cid);
        cf.setFundId(fund.getId());
        Position position = positionDao.findByCustomerFundForUpdate(cf);
        /*
        if (position == null)
            throw new RollbackException("user does not have fund " +
                    String.valueOf(fund.getTicker()));
        if (position.getShares() < position.getPendingShareDecrease() + shares)
            throw new RollbackException(NOENOUGHSHARE);
            */
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
        User user = userDao.findByUserNameForUpdate(userName);
        if (user == null)
            throw new RollbackException();
        user.setCash(user.getCash() + amount);
    }


}
