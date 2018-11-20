package com.msw.devops.service;

import com.msw.devops.entity.UserOnline;

import java.util.List;

public interface SessionService {

	List<UserOnline> list();
	boolean forceLogout(String sessionId);
}
