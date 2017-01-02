package com.rest.service;

import com.SampleController;
import com.rest.mapper.UserPODao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    }

    @Test
    public void update() throws Exception {

    }

}