package com.learning.security;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.dto.UserDTO;
import com.learning.dto.UserDataVO;
import com.learning.dto.UserResponseVO;
import com.learning.exceptions.CustomException;
import com.learning.model.UserConnexion;
import com.learning.model.base.ConstantSecurity;
import com.learning.service.UserAttemptService;
import com.learning.service.UserConnexionService;
import com.learning.service.UserService;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private UserService userService;
	private String email;
	private UserAttemptService userAttemptService;
	private UserConnexionService userConnexionService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = ctx.getBean(JwtTokenProvider.class);
		this.userService = ctx.getBean(UserService.class);
		this.userAttemptService = ctx.getBean(UserAttemptService.class);
		this.userConnexionService=ctx.getBean(UserConnexionService.class);
		setFilterProcessesUrl(ConstantSecurity.URL_LOGIN);
	}

	@ExceptionHandler({ CustomException.class, BadCredentialsException.class })
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			// converting a JSON String to a Java object using the ObjectMapper class
			UserDataVO user = new ObjectMapper().readValue(request.getInputStream(), UserDataVO.class);
			LOGGER.info("User with email {} attempts login ", user.getEmail());
			this.email = user.getEmail();
			// passing the UsernamePasswordAuthenticationToken to the default
			// AuthenticationProvider (ProviderManager) which will use the
			// userDetailsService to get
			// the user based on username and compare that user's password with the one in
			// the authentication token
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

		} catch (IOException e) {
			ResponseFormat.sendErrorResponse(request, response, HttpStatus.BAD_REQUEST, "Bad Request");

		} catch (InternalAuthenticationServiceException e) {
			ResponseFormat.sendErrorResponse(request, response, HttpStatus.BAD_REQUEST, "Bad credentials");
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException {
		User authUser = (User) authResult.getPrincipal();

		LOGGER.info("User with email {} loggedIn successfuly ! ", authUser.getUsername());

		// create TOKEN with jwtTokenProvider
		String token = jwtTokenProvider.createToken(authUser.getUsername(), authUser.getAuthorities());
		// add token to the cookie
		CookieUtil.create(response, token);
		// add token to the user to validate it when user token expired
		com.learning.model.User user = userService.addTokenToUser(authUser.getUsername(), token, null);

//		UserResponseVO loggedInUser = new UserResponseVO();
//		loggedInUser.setUsername(authUser.getUsername());
//		loggedInUser.setAuthorities(authUser.getAuthorities());
//		loggedInUser.setIsNew(user.isNew());
		UserDTO userDTO=userService.convertModelToDTO(user);
		userDTO.setToken(token);
		// unlock userAttempt by email
		userAttemptService.unlock(email);
        //save connexion
		userConnexionService.save(new UserConnexion(user, LocalDate.now()));
		response.setHeader("content-type", MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
		response.getWriter().write(jwtTokenProvider.convertObjectToJson(userDTO));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {

		boolean isLocked = userAttemptService.lock(email);
		if (isLocked) {
			LOGGER.info("User locked account : {}", email);
			ResponseFormat.sendErrorResponse(request, response, HttpStatus.LOCKED, failed.getMessage());
		} else {
			LOGGER.error("User Authentication failed");
			ResponseFormat.sendErrorResponse(request, response, HttpStatus.UNAUTHORIZED, failed.getMessage());
		}

	}
}
