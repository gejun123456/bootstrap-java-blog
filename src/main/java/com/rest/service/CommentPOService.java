package com.rest.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.rest.domain.CommentPO;
import com.rest.mapper.CommentPODao;

@Service
public class CommentPOService {

    @Resource
    private CommentPODao commentPODao;

    public int insert(CommentPO pojo){
        return commentPODao.insert(pojo);
    }

    public int insertList(List< CommentPO> pojos){
        return commentPODao.insertList(pojos);
    }

    public List<CommentPO> select(CommentPO pojo){
        return commentPODao.select(pojo);
    }

    public int update(CommentPO pojo){
        return commentPODao.update(pojo);
    }

}
