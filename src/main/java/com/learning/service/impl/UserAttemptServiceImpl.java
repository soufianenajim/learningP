package com.learning.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.learning.dao.UserAttemptRepository;
import com.learning.model.User;
import com.learning.model.UserAttempt;
import com.learning.service.UserAttemptService;
import com.learning.service.UserService;

@Service
public class UserAttemptServiceImpl implements UserAttemptService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAttemptServiceImpl.class);

	@Autowired
	private UserAttemptRepository userAttemptRepository;
	@Autowired

	private UserService userService;

	@Override
	public UserAttempt findByEmail(String email) {

		return userAttemptRepository.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {

		return userAttemptRepository.existsByEmail(email);
	}

	@Override
	public void saveOrUpdate(UserAttempt userAttempt) {
		userAttemptRepository.save(userAttempt);

	}

	@Transactional
	public boolean lock(String email) {

		UserAttempt userAttempt = findByEmail(email);
		User user = userService.findUser(email);
		if (null == userAttempt) {
			LOGGER.info("user {} locked for the first time", email);
			userAttempt = new UserAttempt(email, 1, LocalDateTime.now());
			saveOrUpdate(userAttempt);

		} else {
             if(null!=user) {
            	 int nbrAttemptsMax = user.getOrganization().getNbrAttempt();
				 int nbrAttempts = userAttempt.getAttempts();
     			int timeOfBlock = user.getOrganization().getTimeOfBlock();
     			LOGGER.info("number attempts max for user {} are : {}", user.getEmail(), nbrAttemptsMax);
     			LOGGER.info("current attempt for user {} is : {}", user.getEmail(), nbrAttempts);
     			LOGGER.info("time of block for user {} are : {}", user.getEmail(), timeOfBlock);
     			if (user.isLocked()) {
     				if (LocalDateTime.now().isAfter(userAttempt.getDateFirstAttempt().plusMinutes(timeOfBlock))) {
     					user.setLocked(false);
     					userAttempt.setAttempts(0);
     					userAttempt.setDateFirstAttempt(null);
     				}
     			} else {
     				if (userAttempt.getAttempts() + 1 == nbrAttemptsMax) {
						LOGGER.info("user {} exceeded number of attempts, account has been locked", user.getEmail());
     					user.setLocked(true);
     				}

     				userAttempt.setAttempts(userAttempt.getAttempts() + 1);
     				userAttempt.setDateFirstAttempt(LocalDateTime.now());
     			}
             }
			
		}
		return user != null ? user.isLocked() : false;

	}
    
	@Async
	@Transactional
	public void unlock(String email) {
		UserAttempt userAttempt = findByEmail(email);
		if (null != userAttempt) {
			LOGGER.info("Unlock user {}", email);
			userAttempt.setAttempts(0);
			userAttempt.setDateFirstAttempt(null);
		}
	}

}
