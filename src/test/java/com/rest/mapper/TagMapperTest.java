package com.rest.mapper;

import basic.AbstractTest;
import com.rest.domain.ContentTagRelation;
import com.rest.domain.TagPo;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/1/25
 * @Description
 */

public class TagMapperTest extends AbstractTest {
    @Autowired
    private TagPoDao tagPoDao;

    @Autowired
    private ContentTagRelationDao contentTagRelationDao;


    @Before
    public void setUp() {
        tagPoDao.delete();
        contentTagRelationDao.delete();
        for (int i = 0; i < 5; i++) {
            TagPo pojo = new TagPo();
            pojo.setTagName("tag_" + i);
            pojo.setCreateTime(new Date());
            pojo.setUpdateTime(new Date());
            tagPoDao.insert(pojo);
        }

        for (int i = 0; i < 5; i++) {
            ContentTagRelation pojo = new ContentTagRelation();
            pojo.setContentId(i);
            pojo.setTagId(i);
            pojo.setCreateTime(new Date());
            pojo.setUpdateTime(new Date());
            contentTagRelationDao.insert(pojo);
        }
    }


    @After
    public void tearDown() {
        tagPoDao.delete();
        contentTagRelationDao.delete();
    }

    @Test
    public void testFind() {
        List<TagPo> all =
            tagPoDao.findAll();
        Assertions.assertThat(all.size()).isEqualTo(5);
    }

}
