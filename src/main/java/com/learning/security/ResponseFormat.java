package com.learning.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseFormat {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseFormat.class);

	private ResponseFormat() {
		throw new IllegalStateException("Utility class");
	}

	public static void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, HttpStatus status,
			String msg) {

		try {
			SecurityContextHolder.clearContext();
			response.setStatus(status.value());
			response.setHeader("content-type", MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");

			Map<String, Object> data = new HashMap<>();
			data.put("timestamp", new Date());
			data.put("status", status.value());
			data.put("message", msg);
			data.put("path", request.getRequestURL().toString());

			OutputStream out = response.getOutputStream();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, data);
			out.flush();
		} catch (IOException e) {
			LOGGER.error("User Authentication failed  : {} ", e.getMessage());
		}
	}

}
