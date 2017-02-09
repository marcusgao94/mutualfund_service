package com.team11.mutualfund.service;

import com.team11.mutualfund.dao.UserDao;
import com.team11.mutualfund.dao.FundDao;
import com.team11.mutualfund.dao.FundPriceHistoryDao;
import com.team11.mutualfund.dao.PositionDao;
import com.team11.mutualfund.model.*;

import com.team11.mutualfund.utils.Positionvalue;
import com.team11.mutualfund.utils.TransitionFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static com.team11.mutualfund.utils.Constant.*;

@Service
@Transactional
public class FundService {

    @Autowired
    private FundDao fundDao;

    @Autowired
    private FundPriceHistoryDao fundPriceHistoryDao;

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private UserDao userDao;

    public void createFund(Fund fund) throws RollbackException {
        if (fundDao.findByTicker(fund.getTicker()) != null)
            throw new RollbackException(DUPLICATEFUNDTICKER);
        fundDao.saveFund(fund);
    }

    public Fund getFundByTicker(String ticker) {
        return fundDao.findByTicker(ticker);
    }

    public void updateFundPrice(long fid, LocalDate date, double price)
            throws RollbackException {
        Fund fund = fundDao.findById(fid);
        if (fund == null)
            throw new RollbackException(NOFUND);
        FundPriceHistory fundPriceHistory = new FundPriceHistory();
        FundDate fundDate = new FundDate(fund.getId(), date);
        if (fundPriceHistoryDao.findByFundDate(fundDate) != null)
            throw new RollbackException(DUPLICATEFUNDPRICEHISTORY);
        fundPriceHistory.setFundDate(fundDate);
        fundPriceHistory.setPrice(price);
        fundPriceHistory.setFund(fund);
        fundPriceHistoryDao.save(fundPriceHistory);
    }

    //get fund history by fundticker
    public List<FundPriceHistory> getFundPriceHistoryByTicker(String ticker){
        return fundPriceHistoryDao.listByFundTicker(ticker);
    }


    // list all fund with price of last transition day
    public List<TransitionFund> listFundPrice(LocalDate date) {
        List<TransitionFund> transitionFundList = new LinkedList();
        List<Fund> fundList = fundDao.listFund();
        // cannot directly query fundPriceHistory, because new created fund does not have a price
        for (Fund f : fundList) {
            TransitionFund tf = new TransitionFund();
            tf.setFund(f);
            // get previous price
            FundDate fd = new FundDate();
            fd.setFundId(f.getId());
            fd.setDate(date);
            FundPriceHistory fph = fundPriceHistoryDao.findByFundDate(fd);
            if (fph != null) {
                DecimalFormat df = new DecimalFormat("#0.00");
                tf.setLastPrice(df.format(fph.getPrice()));
            }
            transitionFundList.add(tf);
        }
        return transitionFundList;
    }

    //list all available funds for purchasing
    public List<Fund> listFund() {
        return fundDao.listFund();
    }
    
    //list funds that a customer purchased
    public List<Positionvalue> listPositionvalueByCustomerId(long cid) {
        List<Positionvalue> positionvalueList = new LinkedList<>();
        List<Position> positionList = positionDao.listByCustomerId(cid);
        for (Position p : positionList) {
            List<FundPriceHistory> fph = fundPriceHistoryDao.listByFundId(p.getFund().getId());
            double price = 0d;
            if (fph != null & !fph.isEmpty())
                price = fph.get(0).getPrice();
            Positionvalue pv = new Positionvalue();
            pv.setFund(p.getFund());
            pv.setShares(p.getShares());
            pv.setPrice(price);
            pv.setValue(p.getShares() * price);
            pv.setAvailable(p.getShares() - p.getPendingShareDecrease());
            positionvalueList.add(pv);
        }
        return positionvalueList;
    }


}

