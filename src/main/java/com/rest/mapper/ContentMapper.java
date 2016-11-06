package com.rest.mapper;

import com.rest.domain.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Mapper
public interface ContentMapper {
    int addContent(Content content);
}
