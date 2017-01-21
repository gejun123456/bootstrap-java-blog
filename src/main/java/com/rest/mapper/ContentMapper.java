package com.rest.mapper;

import com.rest.domain.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Mapper
public interface ContentMapper {

    int insert(Content content);

    List<Content> getPageWithStatus(@Param("status") int status, @Param("start") int start, @Param("limit") int limit);

    int getCount();

    Optional<Content> findById(int id);

    int updateContent(@Param("pojo") Content content);

    int deletebyId(@Param("pojo") Content content);

    int updateStatusById(@Param("updatedStatus") Integer updatedStatus, @Param("id") Integer id);

    Optional<String> findUserIdById(@Param("id") Integer id);

    List<Content> findByUserId(@Param("userId") Integer userId);


    List<Content> findByStatusOrderByAddtimeDesc(@Param("status") Integer status);
}
