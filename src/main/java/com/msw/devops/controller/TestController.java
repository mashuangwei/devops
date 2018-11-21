package com.msw.devops.controller;

import com.msw.devops.annotation.Log;
import com.msw.devops.exception.Result;
import com.msw.devops.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
