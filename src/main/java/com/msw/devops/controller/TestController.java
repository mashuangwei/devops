package com.msw.devops.controller;

import com.msw.devops.annotation.Log;
import com.msw.devops.entity.User;
import com.msw.devops.exception.Result;
import com.msw.devops.util.RedisUtil;
import com.msw.devops.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author mashuangwei
 * @date 2018-11-21 13:37
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger( TestController.class );
    @Autowired
    private RedisUtil redisUtil;
    @GetMapping("/test")
    public Result getUser(){
        logger.info("info= {}", "test info");
        logger.error("error= {}", "test error");
        logger.debug("debug= {}", "test debug");
        return ResultUtil.success(200);
    }

    @PostMapping("/test1")
    public Result getUser1( User user){
        return ResultUtil.success(200,user);
    }

    @GetMapping("/redis")
    public Result redisTest(){
        User user = new User();
        user.setStatus("1");
        user.setUsername("msw");
        user.setCreateTime(new Date());
        user.setId(1);
        user.setPassword("12345");
        redisUtil.set("user", user);
        User user1 = (User)redisUtil.get("user");
        logger.info(user1.toString());
        return ResultUtil.success(200, user);
    }

}
