package com.learning;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.learning.dto.UserDTO;
import com.learning.service.UserService;

@SpringBootApplication
public class LearningApplication {

	

	public static void main(String[] args) {
		
		
		ApplicationContext ctx =  SpringApplication.run(LearningApplication.class, args);
		UserService userService = ctx.getBean(UserService.class);
//		UserDTO u=new UserDTO();
//		u.setFirstName("rahma");
//		u.setLastName("hamza");
//		userService.save(u);

	}

}
