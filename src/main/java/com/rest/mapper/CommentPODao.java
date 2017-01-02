package com.rest.mapper;

import com.rest.domain.CommentPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentPODao {

    int insert(@Param("pojo") CommentPO pojo);

    int insertList(@Param("pojos") List< CommentPO> pojo);

    CommentPO findById(@Param("id")Integer id);

    List<CommentPO> findByArticleId(@Param("article_id")Integer article_id);

    int delete(int id);
}
