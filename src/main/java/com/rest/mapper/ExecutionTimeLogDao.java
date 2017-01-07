package com.rest.mapper;

import com.rest.domain.ExecutionTimeLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExecutionTimeLogDao {

    int insert(@Param("pojo") ExecutionTimeLog pojo);

    int insertList(@Param("pojos") List<ExecutionTimeLog> pojo);

    int update(@Param("pojo") ExecutionTimeLog pojo);

}

