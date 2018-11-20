package com.msw.devops.controller;

import java.util.List;

import com.msw.devops.entity.UserOnline;
import com.msw.devops.exception.Result;
import com.msw.devops.service.SessionService;
import com.msw.devops.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/online")
public class SessionController {

	@Autowired
	SessionService sessionService;

	@RequestMapping("index")
	public String online() {
		return "online";
	}

	@ResponseBody
	@RequestMapping("list")
	public List<UserOnline> list() {
		return sessionService.list();
	}

	@ResponseBody
	@RequestMapping("forceLogout")
	public Result forceLogout(String id) {
		try {
			sessionService.forceLogout(id);
			return ResultUtil.success(200);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(400,"踢出用户失败");
		}

	}
}
