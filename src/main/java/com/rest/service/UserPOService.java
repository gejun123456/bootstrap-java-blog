package com.rest.service;

import com.rest.domain.UserPO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.rest.mapper.UserPODao;

@Service
public class UserPOService {

    @Resource
    private UserPODao userPODao;

    public int insert(UserPO pojo){
        return userPODao.insert(pojo);
    }

    public int insertList(List< UserPO> pojos){
        return userPODao.insertList(pojos);
    }

    public List<UserPO> select(UserPO pojo){
        return userPODao.select(pojo);
    }

    public int update(UserPO pojo){
        return userPODao.update(pojo);
    }


    public int getCount(){
        return userPODao.getCount();
    }

}
