package com.rest.mapper;

import basic.AbstractTest;
import com.rest.domain.ExecutionTimeLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.Assert.*;

/**
 * Created by bruce.ge on 2017/1/8.
 */
public class ExecutionTimeLogDaoTest extends AbstractTest {

    @Autowired
    private ExecutionTimeLogDao executionTimeLogDao;

    @Test
    public void insert() throws Exception {
        ExecutionTimeLog executionTimeLog = new ExecutionTimeLog();
    }

    @Test
    public void insertList() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

}