package com.rest.mapper;

import com.rest.domain.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserPODao {

    int insert(@Param("pojo") UserPO pojo);

    int insertList(@Param("pojos") List<UserPO> pojo);

    Optional<UserPO> findFirstByUsername(@Param("username")String username);

    List<UserPO> findByUsernameAndCryptpasswod(@Param("username") String username, @Param("cryptpasswod") String cryptpasswod);

    int update(@Param("pojo") UserPO pojo);

    int updatePasswordcookieById(@Param("updatedPasswordcookie") String updatedPasswordcookie, @Param("id") Integer id);

    int delete(@Param("pojo") int id);

    int getCount();

    List<UserPO> findByUsernameAndPasswordcookie(@Param("username") String username, @Param("passwordcookie") String passwordcookie);


    Optional<UserPO> findFirstByUsernameAndPasswordcookie(@Param("username") String username, @Param("passwordcookie") String passwordcookie);

    Optional<UserPO> findFirstByMobile(@Param("mobile")String mobile);

    Optional<UserPO> findFirstByEmail(@Param("email")String email);
}
