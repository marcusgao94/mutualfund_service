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
@Transactional(isolation = Isolation.SERIALIZABLE)
public class TransactionService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FundDao fundDao;

    @Autowired
    private PositionDao positionDao;

    public void buyFund(long uid, String symbol, double amount) throws RollbackException {
        User user = userDao.findByIdForUpdate(uid);
        Fund fund = fundDao.findBySymbolForUpdate(symbol);
        if (user == null || fund == null)
            throw new RollbackException();
        // todo: maybe need edit
        if (user.getCash() < amount)
            throw new RollbackException(NOTENOUGHCASH);

        int shares = (int) (amount / fund.getPrice());
        double realamount = shares * fund.getPrice();
        user.setCash(user.getCash() - realamount);
        CustomerFund cf = new CustomerFund(uid, fund.getId());
        Position position = positionDao.findByCustomerFundForUpdate(cf);
        if (position == null) {
            position = new Position();
            position.setCustomerFund(cf);
            position.setShares(shares);
            position.setUser(user);
            position.setFund(fund);
            positionDao.save(position);
        }
        else {
            position.setShares(position.getShares() + shares);
        }
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
            throw new RollbackException();
        // check enough cash
        if (user.getCash() < amount)
            throw new RollbackException();
        user.setCash(user.getCash() - amount);
    }

    public void depositCheck(String userName, double amount) throws RollbackException {
        User user = userDao.findByUserNameForUpdate(userName);
        if (user == null)
            throw new RollbackException();
        user.setCash(user.getCash() + amount);
    }

}
