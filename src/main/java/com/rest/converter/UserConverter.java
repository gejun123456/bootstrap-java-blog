package com.rest.converter;

import com.rest.bean.User;
import com.rest.domain.UserPO;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public class UserConverter {
    public static User convertToUser(UserPO userPO) {
        User user = new User();
        user.setLogin(true);
        user.setUserId(userPO.getId());
        user.setUserName(userPO.getUsername());
        user.setAdmin(userPO.getAuth() == 1);
        return user;
    }
}
