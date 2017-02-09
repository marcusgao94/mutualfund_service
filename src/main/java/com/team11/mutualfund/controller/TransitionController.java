package com.team11.mutualfund.controller;

import static com.team11.mutualfund.controller.LoginController.checkEmployee;
import static com.team11.mutualfund.utils.Constant.NOTLOGIN;
import static com.team11.mutualfund.utils.Constant.SETTRANSITIONDAY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.team11.mutualfund.service.TransactionService;
import com.team11.mutualfund.service.TransitionService;
import com.team11.mutualfund.utils.TransitionFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team11.mutualfund.form.TransitionForm;
import com.team11.mutualfund.service.FundService;
import com.team11.mutualfund.utils.Constant.*;
import static com.team11.mutualfund.utils.Constant.*;


@Controller
public class TransitionController {

    @Autowired
    private FundService fundService;

    @Autowired
    private TransitionService transitionService;

    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = "/transitionday", method = RequestMethod.GET)
    public String transitionDay(HttpServletRequest request,
                               RedirectAttributes redirectAttributes, Model model) {
        if (!checkEmployee(request)) {
            redirectAttributes.addFlashAttribute("loginError", NOTLOGIN);
            return "redirect:/employee_login";
        }

        TransitionForm transitionForm = new TransitionForm();
        LocalDate date = transitionService.getLastTransitionDay();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        if (date == null)
            transitionForm.setLastDate("no last transition day");
        else {
            String formattedDate = date.format(dtf);
            transitionForm.setLastDate(formattedDate);
        }
        String newDate = LocalDate.now().format(dtf);
        transitionForm.setNewDate(newDate);
        List<TransitionFund> fundList = fundService.listFundPrice(date);
        transitionForm.setFundList(fundList);
        model.addAttribute("transitionForm", transitionForm);
        return "transitionday";
    }

    @RequestMapping(value = "/transitionday", method = RequestMethod.POST)
    public String transitionDay(HttpServletRequest request, Model model,
                               @Valid TransitionForm transitionForm, BindingResult result, RedirectAttributes ra) {
        if (!checkEmployee(request)) {
            ra.addFlashAttribute("loginError", NOTLOGIN);
            return "redirect:/employee_login";
        }
        result.addAllErrors(transitionForm.getValidationErrors());
        if (result.hasErrors()) {
            model.addAttribute("transitionForm", transitionForm);
            return "transitionday";
        }
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(transitionForm.getNewDate(), dtf);
            transitionService.transit(date, transitionForm.getFundList());
        } catch (DateTimeParseException e) {
            result.rejectValue("newDate", "", "date must be the form MM/dd/yyyy");
            return "transitionday";
        } catch (RollbackException e) {
            String message = e.getMessage();
            if (message.startsWith("transition"))
                result.rejectValue("newDate", "", message);
            else
                result.rejectValue("", "", e.getMessage());
            return "transitionday";
        }
        model.addAttribute("success", SETTRANSITIONDAY);
        return "success";
    }

}