package com.rest.domain;

import org.springframework.context.annotation.Bean;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public class UserPO {
    private Integer id;

    private Boolean admin;

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
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
}
