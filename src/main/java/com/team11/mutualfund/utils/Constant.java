package com.team11.mutualfund.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Constant {

    // login
    public static final String NOTLOGIN = "You are not currently logged in";
    public static final String NOTEMPLOYEE = "You must be an employee to perform this action";
    public static final String NOTCUSTOMER = "You must be a customer to perform this action";
    public static final String LOGINERROR = "There seems to be an issue with the " +
            "username/password combination that you entered";
    public static final String LOGOUT = "You have been successfully logged out";

    // user
    public static final String INCONSISTENTPASSWORD = "Password inconsistent";

    // fund
    public static final String CREATEFUND = "The fund was successfully created";
    public static final String NOFUND = "You don’t have any funds in your Portfolio";
    public static final String BUYFUND = "The fund has been successfully purchased";
    public static final String NOTENOUGHCASH = "You don’t have enough cash in your account " +
            "to make this purchase";

    // check
    public static final String DEPOSITCHECK = "The check was successfully deposited";
    public static final String REQUESTCHECK = "The check has been successfully requested";
    public static final String NOTENOUGHREQUEST = "You don’t have sufficient funds in your " +
            "account to cover the requested check";

    // input
    public static final String ILLEGALINPUT = "The input you provided is not valid";

    // success action
    public static final String SUCCESSACTION = "The action was successful";

    // transition day
    public static final String TRANSITIONDAY = "The fund prices have been successfully recalculated";





    public static String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

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

}
