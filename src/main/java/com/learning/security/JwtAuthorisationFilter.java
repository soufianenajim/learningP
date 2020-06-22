package com.learning.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learning.exceptions.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

// We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
public class JwtAuthorisationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorisationFilter.class);
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public JwtAuthorisationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		// resolve the token from cookie
		String token = jwtTokenProvider.resolveToken(httpServletRequest);
		try {

			// check if the new token has not yet expired before validation of the real
			// token
			// this condition return true if new token is empty (first token generated)
			if (jwtTokenProvider.validateTokenExpirationCatchEroor(JwtTokenProvider.newToken)) {
				// check real token is validate or not
				if (token != null) {
					JwtTokenProvider.oldToken = "";
					addAuthenticationToSecuritUpdatedyContext(token);
				}
			} else {
				// initialize the old token to refresh the the token
				JwtTokenProvider.oldToken = "";
				// new has expired so throw ExpiredJwtException to get another new token
				jwtTokenProvider.validateToken(JwtTokenProvider.newToken);
			}

		} catch (ExpiredJwtException e) {
			synchronized (this) {
				// if the oldTOken is already not empty so there are a new token generated
				// before
				// no need to generate another token
				if (StringUtils.isEmpty(JwtTokenProvider.oldToken)) {

					LOGGER.warn("jwt expired needs to refresh");
					// refresh token
					String newToken = jwtTokenProvider.refreshToken(token);

					if (newToken == null) {
						LOGGER.info("refresh token failed logout");
						ResponseFormat.sendErrorResponse(httpServletRequest, httpServletResponse,
								HttpStatus.REQUEST_TIMEOUT, "Request timeout");

						return;
					}

					LOGGER.info("Jwt refresh successfuly");
					// add the newtoken in the cookie
					// add token to the cookie
					CookieUtil.create(httpServletResponse, newToken);

					addAuthenticationToSecuritUpdatedyContext(newToken);

				} else {
				
					addAuthenticationToSecuritUpdatedyContext(JwtTokenProvider.newToken);
				}
			}

		} catch (CustomException | JwtException | IllegalArgumentException ex) {
			// this is very important, since it guarantees the user is not authenticated at
			// all
			LOGGER.error("exception occured in validating token => {}", ex.getMessage());
			ResponseFormat.sendErrorResponse(httpServletRequest, httpServletResponse, HttpStatus.BAD_REQUEST,
					"Invalid request");
			return;
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private void addAuthenticationToSecurityContext(String token) {
		Authentication auth = jwtTokenProvider.getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private void addAuthenticationToSecuritUpdatedyContext(String token) {
		Authentication auth = getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@SuppressWarnings("unchecked")
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (token == null)
			return null;

		Claims claims = jwtTokenProvider.validateToken(token).getBody();
		String user = claims.getSubject();

		if (user == null)
			return null;
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(
				((ArrayList<LinkedHashMap<String, String>>) claims.get("auth")).get(0).get("authority")));
		
		UserDetails userDetails=	org.springframework.security.core.userdetails.User//
		.withUsername(user)//
		.password("")
		.authorities(authorities)//
		
		.credentialsExpired(false)//
		.disabled(false)//
		.build();

		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	}

}
