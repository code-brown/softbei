package org.spring.trainer.service;

import org.spring.trainer.entity.User;
import org.spring.trainer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public User findUserByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found: " + username);
        } else {
            System.out.println("User found: " + username);
        }
        return user;
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUserById(Integer userid) {
        userMapper.deleteUserById(userid);
    }
}
