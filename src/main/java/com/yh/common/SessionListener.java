package com.yh.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session=se.getSession();
		session.setMaxInactiveInterval(3600 );//秒
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
