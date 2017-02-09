package com.team11.mutualfund.controller;

import com.team11.mutualfund.model.User;
import com.team11.mutualfund.service.UserService;
import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.team11.mutualfund.utils.Constant.*;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createCustomerAccount")
    public BasicResponse createCustomer(HttpSession session,
                                        @Valid @RequestBody User user, BindingResult result) {
        BasicResponse br = new BasicResponse();
        if (!checkLogin(session)) {
            br.setMessage(NOTLOGIN);
            return br;
        }
        if (!checkEmployee(session)) {
            br.setMessage(NOTEMPLOYEE);
            return br;
        }
        // validate form
        if (result.hasErrors()) {
            br.setMessage(ILLEGALINPUT);
            return br;
        }
        try {
            user.setRole("customer");
            userService.createCustomer(user);
        } catch (DataIntegrityViolationException e) {
            br.setMessage(ILLEGALINPUT);
            return br;
        }
        br.setMessage(user.getFname() + " was registered successfully");
        return br;
    }

}
