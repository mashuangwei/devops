package com.msw.devops.controller;

import com.msw.devops.annotation.Log;
import com.msw.devops.entity.User;
import com.msw.devops.exception.Result;
import com.msw.devops.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author mashuangwei
 * @date 2018-11-21 13:37
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public Result getUser(){
        return ResultUtil.success(200);
    }

    @PostMapping("/test1")
    public Result getUser1( User user){
        return ResultUtil.success(200,user);
    }

}
