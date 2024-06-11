package org.spring.trainer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.trainer.entity.User;
import org.spring.trainer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://36.138.114.105:30918")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String usertype = user.getUsertype();

        logger.debug("Attempting login with username: {}, usertype: {}", username, usertype);

        if (username == null || password == null || usertype == null) {
            logger.error("Missing username, password, or usertype");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing username, password, or usertype");
        }

        User dbUser = userService.findUserByUsername(username);

        if (dbUser != null) {
            logger.debug("User found: {}", dbUser);
            if (password.equals(dbUser.getPassword()) && usertype.equals(dbUser.getUsertype())) {
                return ResponseEntity.ok("登录成功");
            } else {
                logger.warn("Invalid credentials for username: {}", username);
            }
        } else {
            logger.warn("User not found with username: {}", username);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名、密码或用户类型错误");
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userid}")
    public ResponseEntity<Void> updateUser(@PathVariable Integer userid, @RequestBody User user) {
        user.setUserid(userid);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer userid) {
        userService.deleteUserById(userid);
        return ResponseEntity.ok().build();
    }
}
