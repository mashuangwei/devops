package com.msw.devops.controller;

import com.msw.devops.entity.User;
import com.msw.devops.exception.Result;
import com.msw.devops.mapper.UserMapper;
import com.msw.devops.service.UserService;
import com.msw.devops.util.MD5Utils;
import com.msw.devops.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequiresPermissions("user:user")
    @RequestMapping("list")
    public String userList(Model model) {
        model.addAttribute("value", "获取用户信息");
        return "user";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("/test/add")
    public String userAdd(Model model) {
        model.addAttribute("value", "新增用户");
        return "user";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("delete")
    public String userDelete(Model model) {
        model.addAttribute("value", "删除用户");
        return "user";
    }

    @ResponseBody
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        user.setCreateTime(new Date());

        user.setPassword(MD5Utils.encrypt(user.getUsername(),user.getPassword()));
        int ret = userMapper.addUser(user);
        if (ret > 0) {
            return ResultUtil.success(200, user);
        }
        return ResultUtil.error(400, "添加用户失败", user);

    }
}
