package com.learning.security;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.model.Role;
import com.learning.model.User;
import com.learning.model.base.ConstantSecurity;
import com.learning.service.UserService;
import com.learning.util.UtilityClass;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	public static String oldToken = "";
	public static String newToken = "";

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private long validityInMilliseconds;

	@Value("${security.jwt.token.user.inactivate}")
	private long userInactivateDuration;

	@Autowired
	private DefaultUserDetailsService defaultUserDetailsService;

	@Autowired
	private UserService userService;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	private long convertToMilisecond(long timeInMinutes) {
		return timeInMinutes * 60000;
	}

	public String createToken(String username, Collection<GrantedAuthority> authorities) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", authorities);

		Date now = new Date();
		Date validity = new Date(now.getTime() + convertToMilisecond(validityInMilliseconds));

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public String resolveToken(HttpServletRequest req) {
		return CookieUtil.getValue(req, ConstantSecurity.COOKIE_NAME);
	}

	public Jws<Claims> validateToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	}

	public boolean validateTokenExpirationCatchEroor(String token) {
		if (StringUtils.isEmpty(token)) {
			return true;
		}
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public synchronized String refreshToken(String oldtoken) {

		User user = userService.findUserByToken(oldtoken);

		if(user == null) {
			user = userService.findUserByOldToken(oldtoken);
		}
		ArrayList<Role> roles=new ArrayList<>();

		// check if oldtoken is the same token in the database
		// and check the user's idle time
		if (user != null) {
			long inactiveTime = ChronoUnit.MINUTES.between(user.getTokenDateCreation(), LocalDateTime.now());
			LOGGER.info("User inactive for {} min", inactiveTime);
			if (inactiveTime <= userInactivateDuration) {
				// create the new token and update it in the database
				String newToken = createToken(user.getEmail(),
						roles.stream().map(s -> new SimpleGrantedAuthority(s.getName().toString()))
								.filter(Objects::nonNull).collect(Collectors.toList()));
				userService.addTokenToUser(user.getEmail(), newToken, oldtoken);
				JwtTokenProvider.oldToken = oldtoken;
				JwtTokenProvider.newToken = newToken;
				return newToken;
			}
			LOGGER.info("{} <= {}", inactiveTime, userInactivateDuration);
			LOGGER.info("User session expired , user need to login again");

		}
		LOGGER.info("can't find user with this token {}", oldtoken);
		/*
		 * else { oldToken = ""; String username =
		 * UtilityClass.getUserPrincipal().getUsername(); user =
		 * userService.findUserByEmail(username); return user.getToken(); }
		 */
		oldToken = "";
//		return null;
		return null;
	}

	// return UsernamePasswordAuthenticationToken user if user has role
	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (userService.findUserByToken(token) != null) {
			UserDetails userDetails = defaultUserDetailsService.loadUserByUsername(getUsername(token));
			LOGGER.info("User has for Rules {}  ", userDetails.getAuthorities());
			return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
		}
		LOGGER.info("User has null token ");
		return null;

	}

	public Authentication getAuthentication1(Jws<Claims> token) {
		UserDetails userDetails = UtilityClass.getUserPrincipal();
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String convertObjectToJson(Object object) {
		try {
			if (object == null) {
				return null;
			}
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}
