package com.rest.domain;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public class UserPO {
    private int id;

    private boolean admin;

    private String username;

    private String mobile;

    private String email;

    private String cryptpasswod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
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
}
