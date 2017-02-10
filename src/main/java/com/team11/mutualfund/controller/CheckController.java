package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.DepositCheckForm;
import com.team11.mutualfund.form.RequestCheckForm;
import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.service.TransactionService;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;



import static com.team11.mutualfund.utils.Constant.*;

import java.util.List;


@RestController
public class CheckController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/depositCheck")
    public BasicResponse depositCheck(HttpSession session,
                               @Valid @RequestBody DepositCheckForm dcf, BindingResult result) {

        if (!checkLogin(session))
            return new BasicResponse(NOTLOGIN);
        if (!checkEmployee(session))
            return new BasicResponse(NOTEMPLOYEE);
        if (result.hasErrors())
            return new BasicResponse(ILLEGALINPUT);
        try {
            double cash = Double.valueOf(dcf.getCash());
            /*
            String[] str = ccf.getCash().split(".");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
            */
            transactionService.depositCheck(dcf.getUsername(), cash);
        } catch (Exception e) {
            return new BasicResponse(ILLEGALINPUT);
        }
        return new BasicResponse(DEPOSITCHECK);
    }

    @PostMapping("/requestCheck")
    public BasicResponse requestCheck(HttpSession session,
                               @Valid @RequestBody RequestCheckForm rcf, BindingResult result) {
        if (!checkLogin(session))
            return new BasicResponse(NOTLOGIN);
        if (!checkCustomer(session))
            return new BasicResponse(NOTCUSTOMER);
        if (result.hasErrors())
            return new BasicResponse(ILLEGALINPUT);
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        try {
            double cash = Double.valueOf(rcf.getCashValue());
            /*
            String[] str = ccf.getCash().split(".");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
            */
            transactionService.requestCheck(sessionUser.getUsername(), cash);
        } catch (NumberFormatException e) {
            return new BasicResponse(ILLEGALINPUT);
        } catch (Exception e) {
            return new BasicResponse(NOTENOUGHREQUEST);
        }
        return new BasicResponse(REQUESTCHECK);
    }

}
