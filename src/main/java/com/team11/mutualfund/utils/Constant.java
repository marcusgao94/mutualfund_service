package com.team11.mutualfund.utils;

public class Constant {

    // login
    public static final String NOTLOGIN = "You are not currently logged in";
    public static final String NOTEMPLOYEE = "You must be an employee to perform this action";

    // user
    public static final String USERNAMELENGTH = "Length of user name must be 1 to 20";
    public static final String NOUSERNAME = "User name does not exist";
    public static final String FIRSTNAMELENGTH = "Lenght of first name must be 1 to 20";
    public static final String LASTNAMELENGTH = "Length of last name must be 1 to 20";
    public static final String ADDRESSLENGTH = "Length of address must be 1 to 50";
    public static final String CITYLENGTH = "Length of city must be 1 to 20";
    public static final String STATELENGTH = "Length of state must be 2 to 20";
    public static final String ZIPLENGTH = "Length of zip must be 5 to 6";
    public static final String EMAILLENGTH = "Length of email must be 1 to 30";

    public static final String PASSWORDLENGTH = "Lenght of password must be 1 to 20";
    public static final String INCONSISTENTPASSWORD = "Password inconsistent";
    public static final String WRONGPASSWORD = "Password is wrong";

    public static final String NOCUSTOMER = "User does not exit";

    public static final String NOENOUGHCASH = "Cash not enough";
    public static final String TOOLITTLEAMOUNT = "Amount must >= 0.01";

    public static final String NOENOUGHSHARE = "Share not enough";
    public static final String TOOLITTLESHARE = "Share must >= 0.001";

    public static final String NOPOSITION = "This customer does not have this fund";

    public static final String NOFUND = "Fund does not exist";
    public static final String NOFUNDPRICE = "Fund has no price";
    public static final String DUPLICATEFUNDTICKER = "Fund ticker already exists";
    public static final String NOFUNDPRICEHISTORY = "Price of this fund on this date is not set";
    public static final String DUPLICATEFUNDPRICEHISTORY = "Price of this fund on this date " +
            "has set";
    public static final String NEWFUNDCREATED = "New fund has been created, please refresh";
    public static final String WRONGTRANSITIONDAY = "Transition day must after last transition day";

    public static final String REQUESTCHECKSUCCESS = "Your request has been submitted successfully! "
    		+ "Please wait for the next transition day!";

    public static final String SETTRANSITIONDAY = "New transition day has been set successfully!";

    // input
    public static final String ILLEGALINPUT = "The input you provided is not valid";



    public static String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

}
