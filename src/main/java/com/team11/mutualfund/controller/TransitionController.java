package com.team11.mutualfund.controller;

import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.service.TransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.team11.mutualfund.utils.Constant.*;

@RestController
public class TransitionController {

    @Autowired
    private TransitionService transitionService;

    @PostMapping(value = "/transitionDay")
    public BasicResponse transitionDay(HttpSession session) {
        if (!checkLogin(session))
            return new BasicResponse(NOTLOGIN);
        if (!checkEmployee(session))
            return new BasicResponse(NOTEMPLOYEE);
        transitionService.transit();
        return new BasicResponse(TRANSITIONDAY);
    }

}