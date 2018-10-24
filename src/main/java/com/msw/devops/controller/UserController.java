package com.msw.devops.controller;

import com.msw.devops.entity.User;
import com.msw.devops.mapper.UserMapper;
import com.msw.devops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public List<User> getUserList(){
        return userService.list(null);
    }

    @GetMapping("/query/{id}")
    public User getUserList(@PathVariable Integer id){
        return userMapper.selectByMsw(id);
    }

    @PostMapping("/add")
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
