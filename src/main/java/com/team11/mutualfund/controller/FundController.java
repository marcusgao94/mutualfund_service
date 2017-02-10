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
            return new BasicResponse(ILLEGALINPUT);
        }
        return new BasicResponse(BUYFUND);
    }

    /*
    @RequestMapping(value = "buy_fund", method = RequestMethod.POST)
    public String buyFund(HttpServletRequest request, RedirectAttributes ra,
                          @ModelAttribute("customerPosition") LinkedList<Positionvalue> pv,
                          @ModelAttribute("fundList") LinkedList<Fund> fundList,
                          @Valid BuyFundForm buyFundForm, BindingResult result, Model model) {
        if (!checkCustomer(request)) {
            return "redirect:/customer_login";
        }
        result.addAllErrors(buyFundForm.getValidationError());
        if (result.hasErrors())
            return "buy_fund";
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
        User c = userService.getCustomerByUserName(sessionUser.getUserName());
        try {
            transactionService.buyFund(sessionUser.getId(), buyFundForm.getFundTicker(),
                buyFundForm.getAmount());
        } catch (RollbackException e) {
            String message = e.getMessage();
            if (message.startsWith("customer"))
                result.rejectValue("", "0", message);
            else if (message.startsWith("fund"))
                result.rejectValue("fundTicker", "", message);
            else
                result.rejectValue("amount", "", message);
            return "buy_fund";
        }
        model.addAttribute("success", "Transaction has been submitted successfully, please wait for the next transition day!");
        return "success";
    }

    @RequestMapping("sell_fund")
    public String sellFund(HttpServletRequest request, RedirectAttributes ra, Model model) {
        if (!checkCustomer(request)) {
            return "redirect:/customer_login";
        }
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
        User c = userService.getCustomerByUserName(sessionUser.getUserName());
        List<Positionvalue> pv = fundService.listPositionvalueByCustomerId(c.getId());
        model.addAttribute("customerPosition", pv);
        SellFundForm sellFundForm = new SellFundForm();
        model.addAttribute("sellFundForm", sellFundForm);
        return "sell_fund";
    }

    @RequestMapping(value = "sell_fund", method = RequestMethod.POST)
    public String sellFund(HttpServletRequest request, RedirectAttributes ra,
                           @Valid SellFundForm sellFundForm, BindingResult result, Model model,
                           @ModelAttribute("customerPosition") LinkedList<Positionvalue> pv) {
        if (!checkCustomer(request)) {
            return "redirect:/customer_login";
        }
        result.addAllErrors(sellFundForm.getValidationError());
        if (result.hasErrors())
            return "sell_fund";
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
        try {
            transactionService.sellFund(sessionUser.getId(), sellFundForm.getFundTicker(), sellFundForm.getShare());
        } catch (RollbackException e) {
            String message = e.getMessage();
            if (message.startsWith("customer id"))
                result.rejectValue("", "0", message);
            else if (message.startsWith("fund") || message.startsWith("customer does not")) {
                result.rejectValue("fundTicker", "1", message);
            }
            else
                result.rejectValue("share", "2", message);
            return "sell_fund";
        }
        model.addAttribute("success", "Transaction has been submitted successfully, please wait for the next transition day!");
        return "success";
    }
    */
}
