package com.rest.domain;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public class UserPO {
    private Integer id;

    private Integer auth;

    private String username;

    private String mobile;

    private String email;

    private String cryptpasswod;

    private String passwordcookie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCryptpasswod() {
        return cryptpasswod;
    }

    public void setCryptpasswod(String cryptpasswod) {
        this.cryptpasswod = cryptpasswod;
    }

    public String getPasswordcookie() {
        return passwordcookie;
    }

    public void setPasswordcookie(String passwordcookie) {
        this.passwordcookie = passwordcookie;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public Integer getAuth() {
        return auth;
    }

}
