package com.team11.mutualfund.controller;

import static com.team11.mutualfund.controller.LoginController.checkEmployee;
import static com.team11.mutualfund.utils.Constant.NOTLOGIN;

import java.util.Locale;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team11.mutualfund.form.CreateFundForm;
import com.team11.mutualfund.model.Fund;
import com.team11.mutualfund.service.FundService;


@Controller
public class CreateFundController {

    @Autowired
    private FundService fundService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/employee_createfund", method = RequestMethod.GET)
    public String createFund(HttpServletRequest request,
                               RedirectAttributes redirectAttributes, Model model) {

        if (!checkEmployee(request)) {
            redirectAttributes.addFlashAttribute("loginError", NOTLOGIN);
            return "redirect:/employee_login";
        }

        CreateFundForm createFundForm = new CreateFundForm();
        model.addAttribute("createFundForm", createFundForm);
        return "employee_createfund";
    }

    @RequestMapping(value = "/employee_createfund", method = RequestMethod.POST)
    public String createFund(HttpServletRequest request, Model model,
                             @Valid CreateFundForm createFundForm, BindingResult result,
                             RedirectAttributes ra) {
        if (!checkEmployee(request)) {
            ra.addFlashAttribute("loginError", NOTLOGIN);
            return "redirect:/employee_login";
        }
        if (result.hasErrors())
            return "employee_createfund";

        Fund fund = new Fund(createFundForm);
        try {
            fundService.createFund(fund);
            model.addAttribute("success", "fund " + fund.getName() + " created successfully");
            return "success";
        } catch (RollbackException e){
            result.rejectValue("fundTicker", "", e.getMessage());
            return "employee_createfund";
        }
    }

}
