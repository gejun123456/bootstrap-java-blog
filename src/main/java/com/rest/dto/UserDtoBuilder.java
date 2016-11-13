package com.rest.dto;

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

    public UserDto build() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setAdmin(admin);
        userDto.setUsername(username);
        userDto.setMobile(mobile);
        userDto.setEmail(email);
        return userDto;
    }
}
