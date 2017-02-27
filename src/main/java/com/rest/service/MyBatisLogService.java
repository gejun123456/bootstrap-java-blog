package com.rest.service;

import com.rest.domain.MyBatisLog;
import com.rest.mapper.MyBatisLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyBatisLogService{

    @Resource
    private MyBatisLogDao myBatisLogDao;

    public int insert(MyBatisLog pojo){
        return myBatisLogDao.insert(pojo);
    }

    public int insertSelective(MyBatisLog pojo){
        return myBatisLogDao.insertSelective(pojo);
    }

    public int insertList(List<MyBatisLog> pojos){
        return myBatisLogDao.insertList(pojos);
    }

    public int update(MyBatisLog pojo){
        return myBatisLogDao.update(pojo);
    }
}
