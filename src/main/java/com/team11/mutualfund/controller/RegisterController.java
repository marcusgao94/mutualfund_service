package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.CreateCustomerForm;
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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.DoubleSummaryStatistics;

import static com.team11.mutualfund.utils.Constant.*;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createCustomerAccount")
    public BasicResponse createCustomer(HttpSession session,
                                        @Valid @RequestBody CreateCustomerForm ccf, BindingResult result) {
        BasicResponse br = new BasicResponse();
        /*
        if (!checkLogin(session)) {
            br.setMessage(NOTLOGIN);
            return br;
        }
        if (!checkEmployee(session)) {
            br.setMessage(NOTEMPLOYEE);
            return br;
        }
        */
        // validate form
        if (result.hasErrors()) {
            br.setMessage(ILLEGALINPUT);
            return br;
        }
        User user = new User(ccf);
        try {
            double cash = Double.valueOf(ccf.getCash());
            /*
            String[] str = ccf.getCash().split(".");
            if (str.length == 2 && str[1].length() > 2) {
                br.setMessage(ILLEGALINPUT);
                return br;
            }
            */
            user.setCash(cash);
            userService.createCustomer(user);
        } catch (NumberFormatException | DataIntegrityViolationException e) {
            br.setMessage(ILLEGALINPUT);
            return br;
        }
        br.setMessage(user.getFname() + " was registered successfully");
        return br;
    }

}
