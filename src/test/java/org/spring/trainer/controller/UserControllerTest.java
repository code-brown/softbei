package org.spring.trainer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.trainer.entity.User;
import org.spring.trainer.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.findAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testLoginSuccess() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setUsertype("admin");

        User dbUser = new User();
        dbUser.setUsername("testuser");
        dbUser.setPassword("password");
        dbUser.setUsertype("admin");

        when(userService.findUserByUsername("testuser")).thenReturn(dbUser);

        ResponseEntity<?> response = userController.login(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("登录成功", response.getBody());
    }

    @Test
    public void testLoginFailure() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("wrongpassword");
        user.setUsertype("admin");

        User dbUser = new User();
        dbUser.setUsername("testuser");
        dbUser.setPassword("password");
        dbUser.setUsertype("admin");

        when(userService.findUserByUsername("testuser")).thenReturn(dbUser);

        ResponseEntity<?> response = userController.login(user);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("用户名、密码或用户类型错误", response.getBody());
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("newuser");

        doNothing().when(userService).addUser(any(User.class));

        ResponseEntity<Void> response = userController.addUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("updateduser");

        doNothing().when(userService).updateUser(any(User.class));

        ResponseEntity<Void> response = userController.updateUser(1, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteUserById() {
        doNothing().when(userService).deleteUserById(anyInt());

        ResponseEntity<Void> response = userController.deleteUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindUserByUsername() {
        User dbUser = new User();
        dbUser.setUsername("testuser");
        dbUser.setPassword("password");
        dbUser.setUsertype("admin");

        when(userService.findUserByUsername("testuser")).thenReturn(dbUser);

        ResponseEntity<User> response = userController.findUserByUsername("testuser");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testuser", response.getBody().getUsername());
        assertEquals("password", response.getBody().getPassword());
        assertEquals("admin", response.getBody().getUsertype());
    }
}
