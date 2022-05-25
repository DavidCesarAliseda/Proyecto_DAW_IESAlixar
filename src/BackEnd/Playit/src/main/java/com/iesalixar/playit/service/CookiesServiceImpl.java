package com.iesalixar.playit.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class CookiesServiceImpl {

	public String getUserIdOnSession(HttpServletRequest request) {

		if (request != null) {
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userId")) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	public void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
		if (request != null) {
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userId")) {
					Cookie cookie = cookies[i];
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}
}
