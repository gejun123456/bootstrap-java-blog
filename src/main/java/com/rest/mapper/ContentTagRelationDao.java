package com.rest.mapper;

import com.rest.domain.ContentTagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContentTagRelationDao {

    int insert(@Param("pojo") ContentTagRelation pojo);

    int insertList(@Param("pojos") List< ContentTagRelation> pojo);

    int update(@Param("pojo") ContentTagRelation pojo);

    int delete();

    int deleteById(@Param("id")Integer id);
}
