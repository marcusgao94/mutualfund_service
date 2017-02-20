package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.DepositCheckForm;
import com.team11.mutualfund.form.RequestCheckForm;
import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.service.TransactionService;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.team11.mutualfund.controller.UserController.*;
import static com.team11.mutualfund.utils.Constant.*;

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
            if (cash <= 0)
                return new BasicResponse(ILLEGALINPUT);
            String[] str = dcf.getCash().split("\\.");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
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
            if (cash <= 0)
                return new BasicResponse(ILLEGALINPUT);
            String[] str = rcf.getCashValue().split("\\.");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
            transactionService.requestCheck(sessionUser.getUsername(), cash);
        } catch (NumberFormatException e) {
            return new BasicResponse(ILLEGALINPUT);
        } catch (Exception e) {
            return new BasicResponse(NOTENOUGHREQUEST);
        }
        return new BasicResponse(REQUESTCHECK);
    }

}
