package com;

import basic.AbstractTest;
import com.rest.domain.Content;
import com.rest.domain.ContentTime;
import com.rest.domain.UserPO;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTimeMapper;
import com.rest.mapper.UserPODao;
import jodd.util.BCrypt;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.rule.OutputCapture;

/**
 * Created by bruce.ge on 2016/10/23.
 */
public class MybatisApplicationTest extends AbstractTest{
    @ClassRule
    public static OutputCapture out = new OutputCapture();


    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ContentTimeMapper contentTimeMapper;

    @Autowired
    private UserPODao userPODao;


    @Test
    public void testInsert(){
        Content content = new Content();
        content.setTitle("nimei");
        content.setSource_content("hehe");
        content.setHtml_content("hehe");
        int i = contentMapper.insert(content);
        System.out.println(content.getId());
    }

    @Test
    public void testInsertContentTime(){
        ContentTime time = new ContentTime();
        time.setYear(2016);
        time.setMonth(11);
        time.setDay(6);
        time.setContent_id(6);
        int insert = contentTimeMapper.insert(time);
        System.out.println(insert);
    }

    @Test
    public void testApo(){

    }


    @Test
    public void testInsertUserPo(){
        UserPO po = new UserPO();
        po.setAuth(1);
        po.setUsername("bruce");
        po.setMobile("1233");
        po.setEmail("xxx@163.com");
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw);
        po.setCryptpasswod(hashpw);
        System.out.println(po.getId());
    }

}
