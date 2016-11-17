package com.rest.service;

import com.SampleController;
import com.rest.domain.UserPO;
import com.rest.mapper.UserPODao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bruce.ge on 2016/11/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleController.class)
public class UserPOServiceTest {

    @Autowired
    private UserPODao userPODao;
    @Test
    public void insert() throws Exception {

    }

    @Test
    public void insertList() throws Exception {

    }

    @Test
    public void select() throws Exception {
        UserPO pojo = new UserPO();
        pojo.setUsername("bruce");
        List<UserPO> select =
                userPODao.select(pojo);
        System.out.println(select.size());
    }

    @Test
    public void update() throws Exception {

    }

}