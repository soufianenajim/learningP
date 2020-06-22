package com.learning.security;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.learning.model.base.ConstantSecurity;

@Component
public class CookieUtil {
	@Value("${server.address}")
	private String adress;

	private static  String domaine;
	@PostConstruct
	public void init() throws Exception {
		domaine=adress;
	}

	public static void create(HttpServletResponse httpServletResponse, String value) {
		
		Cookie cookie = new Cookie(ConstantSecurity.COOKIE_NAME, value);
		cookie.setSecure(ConstantSecurity.COOKIE_SECURE);
		cookie.setHttpOnly(ConstantSecurity.COOKIE_HTTP_ONLY);
		cookie.setMaxAge(ConstantSecurity.COOKIE_MAXAAGE);
		cookie.setDomain(domaine);
		cookie.setPath(ConstantSecurity.COOKIE_PATH);
		httpServletResponse.addCookie(cookie);
	}

	public static void clear(HttpServletResponse httpServletResponse, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		cookie.setDomain(domaine);
		httpServletResponse.addCookie(cookie);
	}

	public static String getValue(HttpServletRequest httpServletRequest, String name) {
		Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
		return cookie != null ? cookie.getValue() : null;
	}

//	private CookieUtil() {
//		throw new IllegalStateException("Utility class");
//	}
}
