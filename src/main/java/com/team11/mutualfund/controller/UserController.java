package com.team11.mutualfund.controller;

import com.team11.mutualfund.form.CreateCustomerForm;
import com.team11.mutualfund.form.LoginForm;
import com.team11.mutualfund.model.User;
import com.team11.mutualfund.response.ViewPortfolioResponse;
import com.team11.mutualfund.service.FundService;
import com.team11.mutualfund.service.UserService;
import com.team11.mutualfund.response.BasicResponse;
import com.team11.mutualfund.utils.Positionvalue;
import com.team11.mutualfund.utils.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.text.DecimalFormat;
import java.util.List;

import static com.team11.mutualfund.utils.Constant.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FundService fundService;

    public static boolean checkLogin(HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        return sessionUser != null;
    }

    public static boolean checkEmployee(HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        return (sessionUser != null) && sessionUser.getRole().equals("Employee");
    }

    public static boolean checkCustomer(HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        return (sessionUser != null) && sessionUser.getRole().equals("Customer");
    }

    @PostMapping("/createCustomerAccount")
    public BasicResponse createCustomer(HttpSession session,
                                        @Valid @RequestBody CreateCustomerForm ccf, BindingResult result) {
        if (!checkLogin(session))
            return new BasicResponse(NOTLOGIN);
        if (!checkEmployee(session))
            return new BasicResponse(NOTEMPLOYEE);
        // validate form
        if (result.hasErrors())
            return new BasicResponse(ILLEGALINPUT);

        User user = new User(ccf);
        try {
            double cash = Double.valueOf(ccf.getCash());
            if (cash < 0)
                return new BasicResponse(ILLEGALINPUT);
            String[] str = ccf.getCash().split("\\.");
            if (str.length == 2 && str[1].length() > 2)
                return new BasicResponse(ILLEGALINPUT);
            user.setCash(cash);
            userService.createCustomer(user);
        } catch (NumberFormatException | DataIntegrityViolationException e) {
            return new BasicResponse(ILLEGALINPUT);
        }
        return new BasicResponse(user.getFname() + " was registered successfully");
    }

    @PostMapping("/login")
    public BasicResponse login(HttpServletRequest request,
                               @Valid @RequestBody LoginForm loginForm, BindingResult result) {
        if (result.hasErrors())
            return new BasicResponse(LOGINERROR);
        User u = userService.getUserByUserName(loginForm.getUsername());
        if (u == null)
            return new BasicResponse(LOGINERROR);
        if (!u.getPassword().equals(loginForm.getPassword()))
            return new BasicResponse(LOGINERROR);

        // if a session already exists
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        // create new session
        session = request.getSession(true);
        SessionUser sessionUser = new SessionUser(u.getId(), u.getUsername(), u.getRole());
        session.setAttribute("sessionUser", sessionUser);
        session.setMaxInactiveInterval(15 * 60);
        return new BasicResponse("Welcome " + u.getFname());
    }

    @PostMapping("/logout")
    public BasicResponse logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || !checkLogin(session)) {
            return new BasicResponse(NOTLOGIN);
        }
        session.invalidate();
        return new BasicResponse(LOGOUT);
    }

    // customer view portfolio
    @GetMapping("/viewPortfolio")
    public BasicResponse customerViewAccount(HttpSession session) {
        if (!checkLogin(session)) {
            return new BasicResponse(NOTLOGIN);
        }
        if (!checkCustomer(session)) {
            return new BasicResponse(NOTCUSTOMER);
        }
        ViewPortfolioResponse vpr = new ViewPortfolioResponse();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        // set cash
        User user = userService.getUserById(sessionUser.getId());
        DecimalFormat df = new DecimalFormat("#0.00");
        vpr.setCash(df.format(user.getCash()));
        // set funds
        List<Positionvalue> funds = fundService.listPositionvalueByCustomerId(sessionUser.getId());
        if (funds.isEmpty())
            return new BasicResponse(NOFUND);
        vpr.setMessage(SUCCESSACTION);
        vpr.setFunds(funds);
        return vpr;
    }

}
