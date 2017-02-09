package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.CreateCustomerForm;
import com.team11.mutualfund.model.User;
import com.team11.mutualfund.service.UserService;
import com.team11.mutualfund.utils.BasicResponse;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.team11.mutualfund.controller.LoginController.checkEmployee;
import static com.team11.mutualfund.utils.Constant.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createCustomerAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BasicResponse createCustomer(@RequestBody User user) {
        BasicResponse br = new BasicResponse();
        /*
        if (sessionUser == null) {
            br.setMessage(NOTLOGIN);
            return br;
        }
        if (!sessionUser.getRole().equals("Employee")) {
            br.setMessage(NOTEMPLOYEE);
            return br;
        }
        // validate form
        if (errors.hasErrors()) {
            br.setMessage(ILLEGALINPUT);
            return br;
        }

*/
        try {
            userService.createCustomer(user);
        } catch (DataIntegrityViolationException e) {
            br.setMessage(ILLEGALINPUT);
            return br;
        }
        br.setMessage(user.getFname() + "was registered successfully");
        return br;
    }

}
