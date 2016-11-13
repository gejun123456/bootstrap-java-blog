package com.rest.mapper;

import com.rest.domain.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Mapper
public interface ContentMapper {
    int addContent(Content content);

    List<Content> getPage(@Param("start") int start,@Param("limit") int limit);

    int getCount();

    Content findById(int id);

    int updateContent(@Param("pojo") Content content);
}
