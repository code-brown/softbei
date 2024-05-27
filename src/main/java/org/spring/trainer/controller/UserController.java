package org.spring.trainer.controller;

import org.spring.trainer.entity.User;
import org.spring.trainer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 获取所有用户
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    // 根据用户ID获取用户信息
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userid) {
        User user = userService.findUserById(userid);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 添加用户
    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 更新用户信息
    @PutMapping("/{userid}")
    public ResponseEntity<Void> updateUser(@PathVariable Integer userid, @RequestBody User user) {
        user.setUserid(userid);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    // 根据用户ID删除用户
    @DeleteMapping("/{userid}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer userid) {
        userService.deleteUserById(userid);
        return ResponseEntity.ok().build();
    }
}
