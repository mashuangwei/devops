package com.msw.devops.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.msw.devops.annotation.Log;
import com.msw.devops.config.RedisProperties;
import com.msw.devops.entity.AjaxUser;
import com.msw.devops.entity.User;
import com.msw.devops.exception.Result;
import com.msw.devops.util.MD5Utils;
import com.msw.devops.util.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password, @RequestParam(required = false, defaultValue = "false") boolean rememberMe) {
        // 密码MD5加密
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);

        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResultUtil.success(200, subject.getSession());
        } catch (UnknownAccountException e) {
            return ResultUtil.error(400, e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResultUtil.error(400, e.getMessage());
        } catch (LockedAccountException e) {
            return ResultUtil.error(400, e.getMessage());
        } catch (AuthenticationException e) {
            return ResultUtil.error(400, "认证失败！");
        }
    }

    @GetMapping("/user/info")
    @ResponseBody
    public Result getUserInfo(String token){
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("admin");
        arrayList.add("test");
        arrayList.add("mrbird");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("roles", arrayList);
        return ResultUtil.success(200,  jsonObject);
    }

    @PostMapping("/ajaxlogin")
    @ResponseBody
    public Result ajaxlogin(@RequestBody AjaxUser ajaxUser) {
        // 密码MD5加密
        String password = MD5Utils.encrypt(ajaxUser.getUsername(), ajaxUser.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(ajaxUser.getUsername(), password, true);

        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResultUtil.success(200, subject.getSession());
        } catch (UnknownAccountException e) {
            return ResultUtil.error(400, e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResultUtil.error(400, e.getMessage());
        } catch (LockedAccountException e) {
            return ResultUtil.error(400, e.getMessage());
        } catch (AuthenticationException e) {
            return ResultUtil.error(400, "认证失败！");
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        // 登录成后，即可通过Subject获取登录的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/403")
    public String forbid() {
        return "403";
    }

}
