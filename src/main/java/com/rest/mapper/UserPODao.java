package com.rest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.rest.domain.UserPO;
@Mapper
public interface UserPODao {

    int insert(@Param("pojo") UserPO pojo);

    int insertList(@Param("pojos") List< UserPO> pojo);

    List<UserPO> select(@Param("pojo") UserPO pojo);

    int update(@Param("pojo") UserPO pojo);

    int delete(@Param("pojo") int id);

    int getCount();
}
