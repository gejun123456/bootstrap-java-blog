package com.rest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.rest.domain.CommentPO;
@Mapper
public interface CommentPODao {

    int insert(@Param("pojo") CommentPO pojo);

    int insertList(@Param("pojos") List< CommentPO> pojo);

    List<CommentPO> select(@Param("pojo") CommentPO pojo);

    int update(@Param("pojo") CommentPO pojo);

}
