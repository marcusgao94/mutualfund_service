package com.team11.mutualfund.form;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.PASSWORDLENGTH;
import static com.team11.mutualfund.utils.Constant.USERNAMELENGTH;
import static com.team11.mutualfund.utils.Constant.sanitize;


public class LoginForm {

    @Size(min = 1, max = 20, message = USERNAMELENGTH)
    private String username;

    @Size(min = 1, max = 20, message = PASSWORDLENGTH)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = sanitize(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = sanitize(password);
    }
}
