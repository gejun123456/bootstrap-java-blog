package com.rest.mapper;

import com.rest.domain.Archives;
import com.rest.domain.ContentTime;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by bruce.ge on 2016/11/6.
 */
@Mapper
public interface ContentTimeMapper {
    int insert(ContentTime time);

    List<Integer>  getDistinctYears();

    List<Archives> findByYear(int year);
}
