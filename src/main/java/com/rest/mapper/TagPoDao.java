package com.rest.mapper;

import com.rest.domain.TagPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TagPoDao {

    int insert(@Param("pojo") TagPo pojo);

    int insertList(@Param("pojos") List< TagPo> pojo);

    int update(@Param("pojo") TagPo pojo);


    int deleteById(@Param("id")Integer id);


    TagPo findById(@Param("id")Integer id);


    int updateTagNameById(@Param("updatedTagName")String updatedTagName,@Param("id")Integer id);

    List<TagPo> findAll();


    List<TagPo> findIdAndTagName();

    int delete();


    List<String> findTagName();


    List<String> findTagNameByIdIn(@Param("idList")List<Integer> idList);

}
