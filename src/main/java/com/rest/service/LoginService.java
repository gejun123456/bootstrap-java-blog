package com.rest.service;

import com.rest.domain.UserPO;
import com.rest.mapper.UserPODao;
import jodd.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Service
public class LoginService {
    @Autowired
    private UserPODao userPODao;

    public UserPO login(String userName, String password) {
        //logged to system.
        //todo shall add with database.
        List<UserPO> select = userPODao.findByUsername(userName);
        if (select.size() == 0) {
            return null;
        }
        //find from database.  //when it signed, it saved into the database.
        UserPO selected = select.get(0);
        boolean checkpw = BCrypt.checkpw(password, selected.getCryptpasswod());
        if (checkpw) {
            UserPO.UserPOBuilder builder = UserPO.builder().id(selected.getId()).mobile(selected.getMobile()).username(selected.getUsername())
                    .email(selected.getEmail()).auth(selected.getAuth());
            return builder.build();
        }
        return null;
    }

    //todo the cookie shall alway stay the same, shall change base on time. store in database to check.
//    try store cookie value in database. then use it to check.
    public UserPO loginByCookie(String userName, String passwordCookie) {
        //logged to system.
        //to check if it equal to the value.
        //todo shall add with database.
        List<UserPO> select = userPODao.findByUsernameAndPasswordcookie(userName,passwordCookie);
        if (select.size() == 0) {
            return null;
        }
        UserPO selected = select.get(0);
        return UserPO.builder().id(selected.getId()).mobile(selected.getMobile()).username(selected.getUsername())
                .email(selected.getEmail()).auth(selected.getAuth()).build();

    }

    //update cookie value in database.
    public void updateCookie(String random, Integer id) {
        userPODao.updatePasswordcookieById(random, id);
    }
}
