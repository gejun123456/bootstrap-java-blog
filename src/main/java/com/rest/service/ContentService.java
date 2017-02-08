package com.rest.service;
import com.rest.domain.Content;
import com.rest.domain.ContentTagRelation;
import com.rest.domain.ContentTime;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTagRelationDao;
import com.rest.mapper.ContentTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author bruce.ge
 * @Date 2017/1/20
 * @Description
 */
@Service
public class ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ContentTimeMapper contentTimeMapper;

    @Autowired
    private ContentTagRelationDao contentTagRelationDao;

    @Transactional
    public void saveContent(Content content, ContentTime contentTime, List<Integer> tags) {
        contentMapper.insert(content);
        contentTime.setContent_id(content.getId());
        contentTimeMapper.insert(contentTime);
        for (Integer tag : tags) {
            ContentTagRelation pojo = new ContentTagRelation();
            pojo.setContentId(content.getId());
            pojo.setTagId(tag);
            pojo.setCreateTime(new Date());
            pojo.setUpdateTime(new Date());
            contentTagRelationDao.insert(pojo);
        }
    }

}
