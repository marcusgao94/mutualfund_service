package com.team11.mutualfund.response;

public class BasicResponse {
    private String message;

    public BasicResponse() {}
    public BasicResponse(String m) {
        message = m;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
