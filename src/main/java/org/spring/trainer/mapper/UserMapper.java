package org.spring.trainer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.trainer.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    // 查询所有用户
    List<User> findAllUsers();

    // 根据用户ID查询用户
    User findUserById(@Param("userid") Integer userid);

    // 添加用户
    void addUser(User user);

    // 更新用户信息
    void updateUser(User user);

    // 根据用户ID删除用户
    void deleteUserById(@Param("userid") Integer userid);
}
