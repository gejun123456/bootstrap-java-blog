package com.rest.mapper;

import com.rest.domain.MyBatisLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyBatisLogDao {
    int insert(@Param("pojo") MyBatisLog pojo);

    int insertSelective(@Param("pojo") MyBatisLog pojo);

    int insertList(@Param("pojos") List<MyBatisLog> pojo);

    int update(@Param("pojo") MyBatisLog pojo);

    List<MyBatisLog> find();
}
