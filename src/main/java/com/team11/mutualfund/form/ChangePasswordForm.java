package com.team11.mutualfund.form;

import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.team11.mutualfund.utils.Constant.INCONSISTENTPASSWORD;

public class ChangePasswordForm {
    private String originPassword;

    private String newPassword;

    private String confirmNewPassword;

    @NotNull
	private String userName;

    public Errors getValidationErrors() {
        Errors errors = new DirectFieldBindingResult(this, "changePasswordForm");
        if (newPassword != null && !newPassword.equals(confirmNewPassword))
            errors.rejectValue("confirmNewPassword", "", INCONSISTENTPASSWORD);
        return errors;
    }
    
    public String sanitize(String s) {
        return s.replace("&", "&qmp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public String getOriginPassword() {
        return originPassword;
    }

    public void setOriginPassword(String originPassword) {
        this.originPassword = sanitize(originPassword);
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = sanitize(newPassword);
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = sanitize(confirmNewPassword);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = sanitize(userName);
    }
}
