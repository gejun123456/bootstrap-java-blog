package com.rest.mapper;

import basic.AbstractTest;
import com.rest.domain.Content;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by bruce.ge on 2017/1/4.
 */
public class ContentMapperTest extends AbstractTest {
    @Autowired
    private ContentMapper contentMapper;


    @Test
    public void testAdd() {
        Content content = new Content();
        content.setTitle("nima");
        content.setSource_content("woca");
        content.setHtml_content("<p>wori</p>");
        content.setUpdatetime(new Date());
        content.setAddtime(new Date());
        content.setIndex_content("hehe bitch");
        content.setUserId(1);
        content.setStatus(10);
        int i = contentMapper.addContent(content);
        System.out.println(i);
    }
}
