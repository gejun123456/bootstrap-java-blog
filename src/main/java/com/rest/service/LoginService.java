package com.rest.service;

import com.rest.domain.UserPO;
import com.rest.mapper.UserPODao;
import jodd.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Service
public class LoginService {
    @Autowired
    private UserPODao userPODao;

    public Optional<UserPO> login(String userName, String password) {
        //logged to system.
        return userPODao.findFirstByUsername(userName)
                .map((user -> {
                    boolean checkpw = BCrypt.checkpw(password, user.getCryptpasswod());
                    if (checkpw) {
                        return user;
                    } else {
                        return null;
                    }
                }))
                ;
    }

    //todo the cookie shall alway stay the same, shall change base on time. store in database to check.
//    try store cookie value in database. then use it to check.

    public Optional<UserPO> loginByCookie(String userName, String passwordCookie) {
        return userPODao.findFirstByUsernameAndPasswordcookie(userName, passwordCookie);

    }

    //update cookie value in database.
    public void updateCookie(String random, Integer id) {
        userPODao.updatePasswordcookieById(random, id);
    }
}
