package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.BuyFundForm;
import com.team11.mutualfund.form.CreateFundForm;
import com.team11.mutualfund.form.SellFundForm;
import com.team11.mutualfund.model.User;
import com.team11.mutualfund.model.Fund;
import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.service.FundService;
import com.team11.mutualfund.service.TransactionService;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import static com.team11.mutualfund.controller.UserController.*;
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
            // check value > 0
            if (initial_value <= 0)
                return new BasicResponse(ILLEGALINPUT);
            String[] str = cff.getInitial_value().split("\\.");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
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
            if (cash <= 0)
                return new BasicResponse(ILLEGALINPUT);
            String[] str = bff.getCashValue().split("\\.");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
            transactionService.buyFund(sessionUser.getId(), bff.getSymbol(), cash);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message.equals(NOTENOUGHCASHACCOUNT) ||
                    message.equals(NOTENOUGHCASHPROVIDED))
                return new BasicResponse(message);
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
            if (shares <= 0)
                return new BasicResponse(ILLEGALINPUT);
            String[] str = sff.getNumShares().split("\\.");
            if (str.length > 1)
                return new BasicResponse(ILLEGALINPUT);
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
