package org.spring.trainer.service.impl;

import org.spring.trainer.entity.User;
import org.spring.trainer.mapper.UserMapper;
import org.spring.trainer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public User findUserById(Integer userid) {
        return userMapper.findUserById(userid);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer userid) {
        userMapper.deleteUserById(userid);
    }
}
