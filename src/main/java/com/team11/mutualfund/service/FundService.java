package com.team11.mutualfund.service;

import com.team11.mutualfund.dao.FundDao;
import com.team11.mutualfund.dao.PositionDao;
import com.team11.mutualfund.model.*;

import com.team11.mutualfund.utils.Positionvalue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
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
    private PositionDao positionDao;

    public void createFund(Fund fund) throws DataIntegrityViolationException {
        fundDao.saveFund(fund);
    }

    // list funds that a customer purchased
    // todo: need edit
    public List<Positionvalue> listPositionvalueByCustomerId(long cid) {
        List<Positionvalue> positionvalueList = new LinkedList<>();
        List<Position> positionList = positionDao.listByCustomerId(cid);
        for (Position p : positionList) {
            Positionvalue pv = new Positionvalue();
            pv.setFund(p.getFund());
            positionvalueList.add(pv);
        }
        return positionvalueList;
    }


}

