package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.BuyFundForm;
import com.team11.mutualfund.form.CreateFundForm;
import com.team11.mutualfund.form.SellFundForm;
import com.team11.mutualfund.model.User;
import com.team11.mutualfund.model.Fund;
import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.service.UserService;
import com.team11.mutualfund.service.FundService;
import com.team11.mutualfund.service.TransactionService;
import com.team11.mutualfund.utils.Positionvalue;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.text.ParseException;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;

import static com.team11.mutualfund.utils.Constant.*;

@RestController
public class FundController {

    @Autowired
    private FundService fundService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("createFund")
    public BasicResponse createFund(HttpSession session,
                                    @Valid @RequestBody CreateFundForm cff, BindingResult result) {
        if (!checkLogin(session))
            return new BasicResponse(NOTLOGIN);
        if (!checkEmployee(session))
            return new BasicResponse(NOTEMPLOYEE);
        if (result.hasErrors())
            return new BasicResponse(ILLEGALINPUT);

        Fund fund = new Fund(cff);
        try {
            double initial_value = Double.valueOf(cff.getInitial_value());
            /*
            String[] str = ccf.getCash().split(".");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
            */
            fund.setPrice(initial_value);
            fundService.createFund(fund);
        } catch (Exception e) {
            return new BasicResponse(ILLEGALINPUT);
        }
        return new BasicResponse(CREATEFUND);
    }

    @PostMapping("/buyFund")
    public BasicResponse buyFund(HttpSession session,
                                 @Valid @RequestBody BuyFundForm bff, BindingResult result) {
        if (!checkLogin(session))
            return new BasicResponse(NOTLOGIN);
        if (!checkCustomer(session))
            return new BasicResponse(NOTCUSTOMER);
        if (result.hasErrors())
            return new BasicResponse(ILLEGALINPUT);

        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        try {
            double cash = Double.valueOf(bff.getCashValue());
            /*
            String[] str = ccf.getCash().split(".");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
            */
            transactionService.buyFund(sessionUser.getId(), bff.getSymbol(), cash);
        } catch (Exception e) {
            if (e.getMessage().equals(NOTENOUGHCASH))
                return new BasicResponse(NOTENOUGHCASH);
            return new BasicResponse(ILLEGALINPUT);
        }
        return new BasicResponse(BUYFUND);
    }

    @PostMapping("sellFund")
    public BasicResponse sellFund(HttpSession session,
                           @Valid @RequestBody SellFundForm sff, BindingResult result) {
        if (!checkLogin(session))
            return new BasicResponse(NOTLOGIN);
        if (!checkCustomer(session))
            return new BasicResponse(NOTCUSTOMER);
        if (result.hasErrors())
            return new BasicResponse(ILLEGALINPUT);
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        try {
            int shares = Integer.valueOf(sff.getNumShares());
            transactionService.sellFund(sessionUser.getId(), sff.getSymbol(), shares);
        } catch (Exception e) {
            String message = e.getMessage();
            if (e.getMessage().equals(NOTENOUGHSHARES))
                return new BasicResponse(NOTENOUGHSHARES);
            return new BasicResponse(ILLEGALINPUT);
        }
        return new BasicResponse(SELLFUND);
    }
}
