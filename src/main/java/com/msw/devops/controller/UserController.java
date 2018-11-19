package com.msw.devops.controller;

import com.msw.devops.entity.User;
import com.msw.devops.mapper.UserMapper;
import com.msw.devops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    public List<User> getUserList(){
        return userService.list(null);
    }

    @GetMapping("/query/{username}")
    public User getUserList(@PathVariable String username){
        User user = userMapper.findByUserName(username);
        if (user != null) {
            redisTemplate.opsForValue().set("小威", user.toString());
            System.err.println(redisTemplate.opsForValue().get("小威"));
        }
        return user;
    }

    @PostMapping("/add")
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
