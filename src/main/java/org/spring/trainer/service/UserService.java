package org.spring.trainer.service;

import org.spring.trainer.entity.User;

import java.util.List;

public interface UserService {
    // 查询所有用户
    List<User> findAllUsers();

    // 根据用户ID查询用户
    User findUserById(Integer userid);

    // 添加用户
    void addUser(User user);

    // 更新用户信息
    void updateUser(User user);

    // 根据用户ID删除用户
    void deleteUserById(Integer userid);

    //根据用户名查找用户
    List<User> findByUserName(String username);
}
