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
                    .withEmail(selected.getEmail());
            if(userName.equals("bruce")){
                userDtoBuilder.withAdmin(true);
            } else {
                userDtoBuilder.withAdmin(false);
            }
            return userDtoBuilder.build();
        }
        return null;
    }

//todo the cookie shall alway stay the same, shall change base on time. store in database to check.
    public UserPO loginByCookie(String userName, String password){
        //logged to system.
        //todo shall add with database.
        if(userName.equals("bruce")&&password.equals("123")){
            return UserDtoBuilder.anUserDto().withId(1).withAdmin(true).withMobile("123").withUsername("bruce").withEmail("xxx@163.com")
                    .build();
        }
        return null;
    }
}
