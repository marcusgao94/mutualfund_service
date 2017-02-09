package com.team11.mutualfund.controller;

import com.team11.mutualfund.utils.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/home", "/index"})
    public String index() {
        return "home";
    }

    @RequestMapping("/header")
    public String header(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) {
        }
        model.addAttribute("sessionUser", sessionUser);
        return "header";
    }
}
