package org.spring.trainer.controller;

import org.spring.trainer.entity.User;
import org.spring.trainer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @CrossOrigin(origins = "http://localhost:3000") // 允许来自前端3000端口的请求

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

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        // 根据用户名查询数据库获取用户信息
        User dbUser = (User) userService.findByUserName(username);

        // 验证密码是否正确
        if (dbUser != null && bCryptPasswordEncoder.matches(password, dbUser.getPassword())) {
            // 生成认证令牌（示例代码，实际应用中可能会使用更复杂的逻辑）
            String authToken = generateAuthToken(username);

            // 返回认证令牌给前端
            return ResponseEntity.ok(authToken);
        } else {
            // 密码不匹配，返回错误消息
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }

    // 生成认证令牌的示例方法
    private String generateAuthToken(String username) {
        // 在实际应用中，您可能会使用更复杂的逻辑生成JWT令牌
        return "JWT " + username; // 示例代码，实际应用中应该生成真实的JWT令牌
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
