package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.*;
import com.team11.mutualfund.model.*;
import com.team11.mutualfund.service.FundService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

import static com.team11.mutualfund.controller.LoginController.checkCustomer;
import static com.team11.mutualfund.utils.Constant.*;


@Controller
@SessionAttributes("funds")
public class ResearchFundController {

    @Autowired
    private FundService fundService;

    @RequestMapping("/customer_researchfund")
    public String researchFund(HttpServletRequest request,
                               RedirectAttributes redirectAttributes, Model model) {
        if (!checkCustomer(request)) {
            return "redirect:/customer_login";
        }

        ResearchFundForm researchFundForm = new ResearchFundForm();
        model.addAttribute("researchFundForm", researchFundForm);
        List<Fund> funds = fundService.listFund();
        model.addAttribute("funds", funds);
        boolean postMethod = false;
        model.addAttribute("postMethod", postMethod);
        return "customer_researchfund";
       
    }

    @RequestMapping(value = "/customer_researchfund", method = RequestMethod.POST)
    public String researchFund(HttpServletRequest request, Model model,RedirectAttributes ra,
                               @Valid ResearchFundForm researchFundForm, BindingResult result
                               ) {
        if (!checkCustomer(request)) {
            return "redirect:/customer_login";
        }
        
        if (result.hasErrors())
            return "customer_researchfund";
        
        boolean postMethod = true;
        model.addAttribute("postMethod", postMethod);
        
        List<Fund> funds = fundService.listFund();
        model.addAttribute("funds", funds);
        //request.setAttribute("funds", funds);
        
        
        Fund a_Fund = fundService.getFundByTicker(researchFundForm.getTicker());       
       
        if (a_Fund == null) {
            FieldError fundIdNotExisterror = new FieldError("researchFundForm", "fundId", "This fund doesn't exist.");
            result.addError(fundIdNotExisterror);
            return "research_fund";
        }
        
        model.addAttribute("a_fund", a_Fund);
        List<FundPriceHistory> fundPriceHistory = fundService.getFundPriceHistoryByTicker(researchFundForm.getTicker());
        //System.out.println("a");
        //HttpSession session = request.getSession();
        model.addAttribute("fundPriceHistory", fundPriceHistory);
        return "customer_researchfund"; //?
    }
}
