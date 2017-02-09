package com.team11.mutualfund.utils;

public class Pair {
    private boolean res;
    private String message;

    public Pair() {}
    public Pair(boolean b, String s) {
        res = b;
        message = s;
    }

    public boolean getRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
