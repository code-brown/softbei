package org.spring.trainer.service;

import org.spring.trainer.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteUserById(Integer userid);
}
