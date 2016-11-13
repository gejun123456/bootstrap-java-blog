package com.rest.dto;

import com.rest.domain.UserPO;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public final class UserDtoBuilder {
    private int id;
    private boolean admin;
    private String username;
    private String mobile;
    private String email;

    private UserDtoBuilder() {
    }

    public static UserDtoBuilder anUserDto() {
        return new UserDtoBuilder();
    }

    public UserDtoBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserDtoBuilder withAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public UserDtoBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDtoBuilder withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserPO build() {
        UserPO userPO = new UserPO();
        userPO.setId(id);
        userPO.setAdmin(admin);
        userPO.setUsername(username);
        userPO.setMobile(mobile);
        userPO.setEmail(email);
        return userPO;
    }
}
