package com.rest.converter;

import com.rest.bean.User;
import com.rest.bean.UserBuilder;
import com.rest.dto.UserDto;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public class UserConverter {
    public static User convertToUser(UserDto userDto) {
        return UserBuilder.anUser().
                withAdmin(userDto.isAdmin())
                .withLogin(true).
                        withUserId(userDto.getId()).
                        withUserName(userDto.getUsername())
                .build();
    }
}
