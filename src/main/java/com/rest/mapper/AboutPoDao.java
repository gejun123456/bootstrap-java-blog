package com.rest.mapper;

import com.rest.domain.AboutPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AboutPoDao {

    int insert(@Param("pojo") AboutPo pojo);

    int insertList(@Param("pojos") List<AboutPo> pojo);

    int update(@Param("pojo") AboutPo pojo);

    AboutPo findFirst();
}
