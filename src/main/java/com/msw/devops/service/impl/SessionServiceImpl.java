package com.msw.devops.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.msw.devops.entity.User;
import com.msw.devops.entity.UserOnline;
import com.msw.devops.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sessionService")
public class SessionServiceImpl implements SessionService {

	@Autowired
	private RedisSessionDAO sessionDAO;

	@Override
	public List<UserOnline> list() {
		List<UserOnline> list = new ArrayList<>();
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		for (Session session : sessions) {
			UserOnline userOnline = new UserOnline();
			User user = new User();
			SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
			if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
				continue;
			} else {
				principalCollection = (SimplePrincipalCollection) session
						.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				user = (User) principalCollection.getPrimaryPrincipal();
				userOnline.setUsername(user.getUsername());
				userOnline.setUserId(user.getId().toString());
			}
			userOnline.setId((String) session.getId());
			userOnline.setHost(session.getHost());
			userOnline.setStartTimestamp(session.getStartTimestamp());
			userOnline.setLastAccessTime(session.getLastAccessTime());
			userOnline.setTimeout(session.getTimeout());
			userOnline.setStatus("在线");
			list.add(userOnline);
		}
		return list;
	}

	@Override
	public boolean forceLogout(String sessionId) {
		Session session = sessionDAO.readSession(sessionId);
		sessionDAO.delete(session);
		return true;
	}

}
