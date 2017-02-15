package com.team11.mutualfund.service;

import com.team11.mutualfund.dao.*;
import com.team11.mutualfund.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.Random;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class TransitionService {

    @Autowired
    private FundDao fundDao;

    public void transit() throws RollbackException {
        List<Fund> fundList = fundDao.listFundForUpdate();
        Random random = new Random();
        for (Fund fund : fundList) {
            // todo: check new price > 0( need to > 0.01 because in database keeps two digits)
            int sign = random.nextInt(2) * 2 - 1;
            double fluctuate = random.nextDouble() / 10;
            double change = 1 + sign * fluctuate;
            fund.setPrice(fund.getPrice() * change);
        }
    }


}
