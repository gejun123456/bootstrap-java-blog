package com.rest.mapper;
import basic.AbstractTest;
import com.rest.domain.ExecutionTimeLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by bruce.ge on 2017/1/8.
 */
public class ExecutionTimeLogDaoTest extends AbstractTest {

    @Autowired
    private ExecutionTimeLogDao executionTimeLogDao;

    @Test
    public void insert() throws Exception {
        ExecutionTimeLog executionTimeLog = new ExecutionTimeLog();
        executionTimeLog.setClassName("com.rest.hehe");
        executionTimeLog.setMethodName("getName");
        executionTimeLog.setExecutionTime(12321l);
        executionTimeLog.setArgsValue("{cool:a,ooo:b}");
        executionTimeLog.setCreateTime(new Date());
        int insert = executionTimeLogDao.insert(executionTimeLog);
        System.out.println(insert);
    }

    @Test
    public void insertList() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

}