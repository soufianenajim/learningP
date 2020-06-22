package com.learning.util;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UtilityClass {
	private static final Logger logger = LoggerFactory.getLogger(UtilityClass.class);
	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!?*()@_#-$|%^\\\\/+=]).{8,20}$";
	private static final String EMAIL_PATTERN = "^([a-z0-9._%+-]+[@][a-zA-Zé1-9]+[.][a-z]{2,3})+$";
	public static final String PHONE_PATTERN = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";

	public static void trimStringValues(Object model) {
		for (Field field : model.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				Object value = field.get(model);
				if (value != null) {
					if (value instanceof String) {
						String trimmed = (String) value;
						field.set(model, trimmed.trim().replaceAll(" +", " "));
					}

				}
			} catch (Exception e) {
				logger.error("error in trimStringValues {}", e);
			}
		}
	}

	public static String generateRandomPassword() {
		final String characters = "ABCDEFGHIabcdefghi01234JKLMNOPQRjklmnopqr56789STUVWXYZstuvwxyz!?*()@_#-$|%^\\/+=";
		final String result = RandomStringUtils.random(8, characters);
		return result.length() >= 8 && validatorPW(result) ? result : generateRandomPassword();
	}

	public static boolean validatorPW(String pw) {
		final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		return pattern.matcher(pw).matches();
	}

	public static boolean validatorPhone(String phone) {
		final Pattern pattern = Pattern.compile(PHONE_PATTERN);
		return pattern.matcher(phone).matches();
	}

	public static String escapeUnderscore(String input) {
		if (input != null && input != "") {
			if (input.contains("\\_")) {
				return input;
			}
			return input.replace("_", "\\_");
		}
		return "";
	}

	public static String getRolePrincipal() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String role;
		if (principal instanceof UserDetails) {
			role = ((UserDetails) principal).getAuthorities().toArray()[0].toString();
		} else {
			role = principal.toString();
		}
		return role;
	}

	public static UserDetails getUserPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userPrinciPal;
		if (principal instanceof UserDetails) {
			userPrinciPal = ((UserDetails) principal);
			return userPrinciPal;
		}
		return null;
	}

	public static boolean validatorEmail(String email) {
		final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		return pattern.matcher(email).matches();
	}

	public static String cronExpressionBuilder(final LocalDate localDate) {

		final int days = localDate.getDayOfMonth();
		final int month = localDate.getMonth().getValue();
		final int years = localDate.getYear();

		String cronExpress = "0" + " " + "30" + " " + "14" + " " + "26" + " " + "03" + " *";

		return cronExpress;
	}

}