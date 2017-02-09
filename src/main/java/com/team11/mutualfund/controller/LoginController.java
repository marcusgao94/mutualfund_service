package com.team11.mutualfund.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.team11.mutualfund.form.LoginForm;
import com.team11.mutualfund.model.User;
import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.service.UserService;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.team11.mutualfund.utils.Constant.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public BasicResponse loginCustomer(HttpServletRequest request,
                                       @Valid @RequestBody LoginForm loginForm, BindingResult result) {
        BasicResponse br = new BasicResponse();
        if (result.hasErrors()) {
            br.setMessage(LOGINERROR);
            return br;
        }
        User u = userService.getUserByUserName(loginForm.getUsername());
        if (u == null) {
            br.setMessage(LOGINERROR);
            return br;
        }
        if (!u.getPassword().equals(loginForm.getPassword())) {
            br.setMessage(LOGINERROR);
            return br;
        }
        // if a session already exists
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        // create new session
        session = request.getSession(true);
        SessionUser sessionUser = new SessionUser(u.getId(), u.getUsername(), u.getRole());
        session.setAttribute("sessionUser", sessionUser);
        session.setMaxInactiveInterval(15 * 60);

        br.setMessage("Welcome " + u.getFname());
        return br;
    }

    @PostMapping("/logout")
    public BasicResponse logout(HttpServletRequest request) {
        BasicResponse br = new BasicResponse();
        HttpSession session = request.getSession(false);
        if (session == null || !checkLogin(session)) {
            br.setMessage(NOTLOGIN);
            return br;
        }
        session.invalidate();
        br.setMessage(LOGOUT);
        return br;
    }

}
