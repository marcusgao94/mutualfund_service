package com.team11.mutualfund.controller;


import com.team11.mutualfund.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = {"customerList", "employeeList"})
public class ViewAccountController {
    @Autowired
    private TransitionService transitionService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserService employeeService;

    @Autowired
    private FundService fundService;

    /*
    // customer
    @RequestMapping(value = "customer_viewaccount", method = RequestMethod.GET)
    public String customerViewAccount(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        if (!checkCustomer(request)) {
            return "redirect:/customer_login";
        }
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User c = userService.getCustomerById(sessionUser.getId());
        model.addAttribute("customer_account", c);

        LocalDate d = transitionService.getLastTransitionDay();
        if (d == null)
            model.addAttribute("date", "no last transition day");
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            model.addAttribute("date", d.format(dtf));
        }
        List<Positionvalue> pv = fundService.listPositionvalueByCustomerId(c.getId());
        model.addAttribute("customerPosition", pv);
        return "customer_viewaccount";
    }
    */

}
