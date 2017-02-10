package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.DepositCheckForm;
import com.team11.mutualfund.form.RequestCheckForm;
import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.RollbackException;
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

    /*
    @RequestMapping(value = "/request_check", method = RequestMethod.POST)
    public String requestCheck(HttpServletRequest request, RedirectAttributes ra, Model model,
                               @Valid RequestCheckForm requestCheckForm, BindingResult result) {
        if (!checkCustomer(request)) {
            return "redirect:/customer_login";
        }
        if (result.hasErrors())
            return "request_check";
        result.addAllErrors(requestCheckForm.getValidationErrors());
        if (result.hasErrors())
            return "request_check";
        try {
            transactionService.requestCheck(
                requestCheckForm.getUserName(), requestCheckForm.getAmount());
        } catch (RollbackException e) {
            String message = e.getMessage();
            if (message.startsWith("customer"))
                result.rejectValue("customerId", "0", message);
            else
                result.rejectValue("amount", "1", message);
            return "request_check";
        }
        model.addAttribute("success", REQUESTCHECKSUCCESS);
        return "success";
    }
    */

}
