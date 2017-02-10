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

    // cash
    public static final String NOENOUGHCASH = "Cash not enough";
    public static final String TOOLITTLEAMOUNT = "Amount must >= 0.01";

    public static final String NOENOUGHSHARE = "Share not enough";
    public static final String TOOLITTLESHARE = "Share must >= 0.001";

    public static final String NOPOSITION = "This customer does not have this fund";

    // fund
    public static final String CREATEFUND = "The fund was successfully created";
    public static final String NOFUND = "You don’t have any funds in your Portfolio";
    public static final String BUYFUND = "The fund has been successfully purchased";

    // check
    public static final String DEPOSITCHECK = "The check was successfully deposited";

    // input
    public static final String ILLEGALINPUT = "The input you provided is not valid";

    // success action
    public static final String SUCCESSACTION = "The action was successful";




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
