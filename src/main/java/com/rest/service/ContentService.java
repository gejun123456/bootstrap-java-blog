package com.rest.service;

import com.rest.domain.Content;
import com.rest.domain.ContentTime;
import com.rest.mapper.ContentMapper;
import com.rest.mapper.ContentTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void saveContent(Content content, ContentTime contentTime) {
        contentMapper.insert(content);
        contentTime.setContent_id(content.getId());
        contentTimeMapper.insert(contentTime);
    }

}
