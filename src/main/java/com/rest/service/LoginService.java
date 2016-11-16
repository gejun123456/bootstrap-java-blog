package com.rest.service;

import com.rest.domain.UserPO;
import com.rest.dto.UserDtoBuilder;
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
    private UserPOService userPOService;

    public UserPO login(String userName, String password){
        //logged to system.
        //todo shall add with database.
        UserPO query = new UserPO();
        query.setUsername(userName);
        List<UserPO> select = userPOService.select(query);
        if(select.size()==0){
            return null;
        }
        //find from database.  //when it signed, it saved into the database.
        UserPO selected = select.get(0);
        boolean checkpw = BCrypt.checkpw(password, selected.getCryptpasswod());
        if(checkpw){
            UserDtoBuilder userDtoBuilder = UserDtoBuilder.anUserDto().withId(selected.getId()).withMobile(selected.getMobile()).withUsername(selected.getUsername())
                    .withEmail(selected.getEmail()).withAuth(selected.getAuth());
            return userDtoBuilder.build();
        }
        return null;
    }

//todo the cookie shall alway stay the same, shall change base on time. store in database to check.
//    try store cookie value in database. then use it to check.
    public UserPO loginByCookie(String userName, String password){
        //logged to system.
        //to check if it equal to the value.
        //todo shall add with database.
        UserPO query = new UserPO();
        query.setUsername(userName);
        query.setPasswordcookie(password);
        List<UserPO> select = userPOService.select(query);
        if(select.size()==0){
            return null;
        }
        UserPO selected = select.get(0);
        UserDtoBuilder userDtoBuilder = UserDtoBuilder.anUserDto().withId(selected.getId()).withMobile(selected.getMobile()).withUsername(selected.getUsername())
                .withEmail(selected.getEmail()).withAuth(selected.getAuth());
        return userDtoBuilder.build();

    }

    //update cookie value in database.
    public void updateCookie(String random, Integer id) {
        UserPO po = new UserPO();
        //default为""
        po.setId(id);
        po.setPasswordcookie(random);
        userPOService.update(po);
    }
}
