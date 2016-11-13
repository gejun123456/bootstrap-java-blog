package com;

import com.rest.domain.Content;
import com.rest.domain.ContentTime;
import com.rest.domain.UserPO;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTimeMapper;
import com.rest.mapper.UserPODao;
import com.rest.utils.GsonUtils;
import jodd.util.BCrypt;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
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
        int i = contentMapper.addContent(content);
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
    public void testInsertUserPo(){
        UserPO po = new UserPO();
        po.setAdmin(true);
        po.setUsername("bruce");
        po.setMobile("1233");
        po.setEmail("xxx@163.com");
        po.setCryptpasswod(BCrypt.hashpw("123", BCrypt.gensalt()));
        int insert = userPODao.insert(po);
        System.out.println(po.getId());
    }

}
