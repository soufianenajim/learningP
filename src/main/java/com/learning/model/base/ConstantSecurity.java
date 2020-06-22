package com.learning.model.base;
public final class ConstantSecurity {

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_STRING = "token";
	public static final String ROLES = "role";

	// Constants for cookies
	public static final String COOKIE_NAME = "JWT-TOKEN";
	public static final Boolean COOKIE_SECURE = false;
	public static final Boolean COOKIE_HTTP_ONLY = true;
	public static final String COOKIE_PATH = "/";
	public static final Integer COOKIE_MAXAAGE = 2147483647;
	public static final String COOKIE_DOMAIN = "localhost";

	// routes for login
	public static final String URL_LOGIN = "/auth";

	// routes for forgtoPassword
	public static final String FORGOT_PASSWORD = "/user/forgotpwd";

	// routes for timeOfBlock
	public static final String TIME_OF_BLOCK = "/user/timeOfBlock";

	
	private ConstantSecurity() {
		throw new IllegalStateException("Utility class");
	}

}
