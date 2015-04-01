package com.fenglianfinance.bill.model;


import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class SignupForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @NotEmpty
    @Size(min = 6, message = "用户名必须由 6 到 20 位字符组成")
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 6, message = "密码必须由 6 到 20 位字符组成")
    private String password;

    @Valid
    private ImgCaptchaRequest captchaResponse;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ImgCaptchaRequest getCaptchaResponse() {
        return captchaResponse;
    }

    public void setCaptchaResponse(ImgCaptchaRequest captchaResponse) {
        this.captchaResponse = captchaResponse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignupForm{" + "username=" + username + ", password=" + password + ", captchaResponse=" + captchaResponse + '}';
    }

}
