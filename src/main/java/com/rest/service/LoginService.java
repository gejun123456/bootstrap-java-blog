package com.rest.service;

import com.rest.domain.UserPO;
import com.rest.dto.UserDtoBuilder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@Service
public class LoginService {
    public UserPO login(String userName, String password){
        //logged to system.
        //todo shall add with database.
        String pw_hash = BCrypt.hashpw("123", BCrypt.gensalt());
        //find from database.  //when it signed, it saved into the database.
        BCrypt.checkpw(password,pw_hash);
        if(userName.equals("bruce")&&password.equals("123")){
            return UserDtoBuilder.anUserDto().withId(1).withAdmin(true).withMobile("123").withUsername("bruce").withEmail("xxx@163.com")
                    .build();
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
