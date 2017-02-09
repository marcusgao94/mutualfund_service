package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.DepositCheckForm;
import com.team11.mutualfund.form.RequestCheckForm;
import com.team11.mutualfund.model.User;
import com.team11.mutualfund.service.UserService;
import com.team11.mutualfund.service.TransactionService;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



import static com.team11.mutualfund.controller.LoginController.checkCustomer;
import static com.team11.mutualfund.controller.LoginController.checkEmployee;
import static com.team11.mutualfund.utils.Constant.*;

import java.util.List;


@Controller
@SessionAttributes("customerList")
public class CheckController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @RequestMapping("/deposit_check")
    public String depositCheck(HttpServletRequest request, RedirectAttributes ra, Model model,
    						@RequestParam(value = "un", required = false) String userName) {
        if (!checkEmployee(request)) {
            ra.addFlashAttribute("loginError", NOTLOGIN);
            return "redirect:/employee_login";
        }
        if (userName != null) {
            User c = userService.getCustomerByUserName(userName);
            if (c == null)
                return "redirect:/employee_searchcustomer";
            DepositCheckForm dpf = new DepositCheckForm();
            dpf.setUserName(userName);
            model.addAttribute("depositCheckForm", dpf);
            return "deposit_check";
        }
        List<User> userList = userService.getCustomerList();
        model.addAttribute("userList", userList);
        DepositCheckForm depositCheckForm = new DepositCheckForm();
        model.addAttribute("depositCheckForm", depositCheckForm);
        return "deposit_check_fast";
    }

    @RequestMapping(value = "/deposit_check", method = RequestMethod.POST)
    public String depositCheck(HttpServletRequest request, RedirectAttributes ra, Model model,
    		 String fast, @Valid DepositCheckForm depositCheckForm, BindingResult result) {
        if (!checkEmployee(request)) {
            ra.addFlashAttribute("loginError", NOTLOGIN);
            return "redirect:/employee_login";
        }
        if (result.hasErrors())
        	return fast == null? "deposit_check": "deposit_check_fast";
        try {
            transactionService.depositCheck(
                depositCheckForm.getUserName(), depositCheckForm.getAmount());
        } catch (RollbackException e) {
            result.rejectValue("userName", "0", e.getMessage());
            return fast == null? "deposit_check": "deposit_check_fast";
        }
        model.addAttribute("success", "You have successfully deposit check for " + depositCheckForm.getUserName());
        return "success";
    }

    @RequestMapping("/request_check")
    public String requestCheck(HttpServletRequest request, RedirectAttributes ra, Model model) {
        if (!checkCustomer(request)) {
            return "redirect:/customer_login";
        }
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
        User customer = userService.getCustomerById(sessionUser.getId());
        RequestCheckForm requestCheckForm = new RequestCheckForm();
        //requestCheckForm.setUserName(customer.getUserName());
        //double available = customer.getCash() - customer.getPendingCashDecrease();
        //requestCheckForm.setAvailable(available);
        model.addAttribute("requestCheckForm", requestCheckForm);
        return "request_check";
    }

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

}
