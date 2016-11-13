package com.rest.bean;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public final class UserBuilder {
    private boolean login =false;
    private int userId;
    private String userName;
    private boolean admin = false;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withLogin(boolean login) {
        this.login = login;
        return this;
    }

    public UserBuilder withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder withAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public User build() {
        User user = new User();
        user.setLogin(login);
        user.setUserId(userId);
        user.setUserName(userName);
        user.setAdmin(admin);
        return user;
    }
}
