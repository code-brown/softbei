package org.spring.trainer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.trainer.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAllUsers();
    User findUserByUsername(@Param("username") String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteUserById(@Param("id") Integer id); // 修改了参数名

}
