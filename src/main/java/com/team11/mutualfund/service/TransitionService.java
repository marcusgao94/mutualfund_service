package com.team11.mutualfund.service;

import com.team11.mutualfund.dao.*;
import com.team11.mutualfund.model.*;
import com.team11.mutualfund.utils.TransitionFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.time.LocalDate;
import java.util.List;

import static com.team11.mutualfund.utils.Constant.*;
import static com.team11.mutualfund.utils.TransactionType.*;

@Service
@Transactional
public class TransitionService {

    @Autowired
    private FundDao fundDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private FundPriceHistoryDao fundPriceHistoryDao;

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FundService fundService;

    public LocalDate getLastTransitionDay() {
        List<FundPriceHistory> fundPriceHistoryList = fundPriceHistoryDao.listAllOrderByDate();
        if (fundPriceHistoryList == null || fundPriceHistoryList.isEmpty())
            return null;
        return fundPriceHistoryList.get(0).getFundDate().getDate();
    }

    public void executeBuyFund(LocalDate date) throws RollbackException {
        List<Transaction> transactionList = transactionDao.listPendingTransactionByType(BUYFUND);
        for (Transaction t : transactionList) {
            // update execute date
            t.setExecuteDate(date);
            // substract cash in user
            User user = t.getUser();
            user.setCash(user.getCash() - t.getAmount());
            //user.setPendingCashDecrease(user.getPendingCashDecrease() - t.getAmount());
            // calculate shares of this amount
            Fund fund = t.getFund();
            FundDate fundDate = new FundDate(fund.getId(), date);
            FundPriceHistory fundPriceHistory = fundPriceHistoryDao.findByFundDate(fundDate);
            if (fundPriceHistory == null)
                throw new RollbackException(NOFUNDPRICEHISTORY);
            double price = fundPriceHistory.getPrice();
            double shares = t.getAmount() / price;
            t.setPrice(price);
            t.setShares(shares);
            // add user shares of this fund in position
            CustomerFund cf = new CustomerFund();
            cf.setCustomerId(user.getId());
            cf.setFundId(fund.getId());
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
    }

    public void executeSellFund(LocalDate date) throws RollbackException {
        List<Transaction> transactionList = transactionDao.listPendingTransactionByType(SELLFUND);
        for (Transaction t : transactionList) {
            // update execute date
            t.setExecuteDate(date);
            // substract shares in position
            User user = t.getUser();
            Fund fund = t.getFund();
            CustomerFund cf = new CustomerFund();
            cf.setCustomerId(user.getId());
            cf.setFundId(fund.getId());
            Position position = positionDao.findByCustomerFundForUpdate(cf);
            if (position == null)
                throw new RollbackException(NOPOSITION);
            position.setShares(position.getShares() - t.getShares());
            position.setPendingShareDecrease(position.getPendingShareDecrease() - t.getShares());
            if (Math.abs(position.getShares()) < 1e-10)
                positionDao.delete(position);

            // calculate amount of this share
            FundDate fundDate = new FundDate(fund.getId(), date);
            FundPriceHistory fundPriceHistory = fundPriceHistoryDao.findByFundDate(fundDate);
            if (fundPriceHistory == null)
                throw new RollbackException(NOFUNDPRICEHISTORY);
            double price = fundPriceHistory.getPrice();
            double amount = price * t.getShares();
            t.setPrice(price);
            t.setAmount(amount);
            // increase cash in user account
            user.setCash(user.getCash() + amount);
        }
    }

    public void executeDepositCheck(LocalDate date) {
        List<Transaction> transactionList = transactionDao.listPendingTransactionByType(DEPOSITCHECK);
        for (Transaction t : transactionList) {
            t.setExecuteDate(date);
            User user = t.getUser();
            user.setCash(user.getCash() + t.getAmount());
        }
    }

    public void executeRequestCheck(LocalDate date) {
        List<Transaction> transactionList = transactionDao.listPendingTransactionByTypeForUpdate(REQUESTCHECK);
        for (Transaction t : transactionList) {
            t.setExecuteDate(date);
            User user = t.getUser();
            user.setCash(user.getCash() - t.getAmount());
            //user.setPendingCashDecrease(user.getPendingCashDecrease() - t.getAmount());
        }
    }

    public synchronized void transit(LocalDate date, List<TransitionFund> fundList)
            throws RollbackException {
        LocalDate lastDate = getLastTransitionDay();
        if (lastDate != null && !date.isAfter(lastDate))
            throw new RollbackException(WRONGTRANSITIONDAY);
        // execute request, deposit check
        executeRequestCheck(date);
        executeDepositCheck(date);

        // check if new fund is created before transit
        if (fundList.size() != fundService.listFund().size())
            throw new RollbackException(NEWFUNDCREATED);
        // update all fund price
        for (TransitionFund fd : fundList) {
            fundService.updateFundPrice(fd.getFund().getId(), date, fd.getNewPrice());
        }

        // execute buy, sell fund
        executeBuyFund(date);
        executeSellFund(date);
    }


}
