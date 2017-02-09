package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.LoginForm;
import com.team11.mutualfund.model.User;
import com.team11.mutualfund.service.UserService;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.team11.mutualfund.utils.Constant.NOUSERNAME;
import static com.team11.mutualfund.utils.Constant.WRONGPASSWORD;

@Controller
public class LoginController {

    @Autowired
    private UserService employeeService;

    @Autowired
    private UserService userService;

    public static boolean checkEmployee(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        return sessionUser != null && sessionUser.getRole() == "Employee";
    }

    public static boolean checkCustomer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        return sessionUser != null && sessionUser.getRole() == "Customer";
    }

    // customer

    @RequestMapping(value = "/customer_login" , method = RequestMethod.GET)
    public String customerLogin(HttpServletRequest request, Model model,
                                @ModelAttribute("loginError") String lem) {
        if (!lem.isEmpty()) {
            model.addAttribute("error", lem);
        }
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "customer_login";
    }

    @RequestMapping(value = "/customer_login", method = RequestMethod.POST)
    public String loginCustomer(HttpServletRequest request, Model model,
                                @Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors())
            return "customer_login";
        User c = userService.getCustomerByUserName(loginForm.getUserName());
        if (c == null) {
            FieldError userNameExistError = new FieldError("loginForm", "userName", NOUSERNAME);
            result.addError(userNameExistError);
            return "customer_login";
        }
        if (!c.getPassword().equals(loginForm.getPassword())) {
            FieldError wrongPasswordError = new FieldError("loginForm", "password", WRONGPASSWORD);
            result.addError(wrongPasswordError);
            return "customer_login";
        }

        HttpSession session = request.getSession();
        //session.setAttribute("user", new SessionUser(c.getId(), c.getUserName(), 0));
        return "redirect:/home";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/home";
    }

}
