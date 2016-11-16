package com.rest.converter;

import com.rest.bean.User;
import com.rest.bean.UserBuilder;
import com.rest.domain.UserPO;

/**
 * Created by bruce.ge on 2016/11/13.
 */
public class UserConverter {
    public static User convertToUser(UserPO userPO) {
        return UserBuilder.anUser().
                withAdmin(userPO.getAuth()==1)
                .withLogin(true).
                        withUserId(userPO.getId()).
                        withUserName(userPO.getUsername())
                .build();
    }
}
