package com;

import basic.AbstractTest;
import com.rest.mapper.CustomerMapper;
import com.rest.utils.GsonUtils;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleController.class)
public class MybatisApplicationTest{
    @ClassRule
    public static OutputCapture out = new OutputCapture();

    @Autowired
    private CustomerMapper customerMapper;
    @Test
    public void test(){
        GsonUtils.printToGson(customerMapper.selectById(1));
    }
}
