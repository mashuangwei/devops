package com.msw.devops.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.msw.devops.annotation.Log;
import com.msw.devops.config.RedisProperties;
import com.msw.devops.entity.AjaxUser;
import com.msw.devops.entity.User;
import com.msw.devops.exception.Result;
import com.msw.devops.input.LoginUser;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody LoginUser loginUser) {
        // 密码MD5加密
        String password = MD5Utils.encrypt(loginUser.getUsername(), loginUser.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(), password, loginUser.isRememberMe());

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

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Result unauth() {
        return ResultUtil.success(200, "未登录或者登录已过期");
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return ResultUtil.success(200, "注销成功");
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
