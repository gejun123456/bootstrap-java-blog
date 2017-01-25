package com.rest.service;

import com.rest.domain.ContentTagRelation;
import com.rest.mapper.ContentTagRelationDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContentTagRelationService{

    @Resource
    private ContentTagRelationDao contentTagRelationDao;

    public int insert(ContentTagRelation pojo){
        return contentTagRelationDao.insert(pojo);
    }

    public int insertList(List< ContentTagRelation> pojos){
        return contentTagRelationDao.insertList(pojos);
    }

    public int update(ContentTagRelation pojo){
        return contentTagRelationDao.update(pojo);
    }

}
