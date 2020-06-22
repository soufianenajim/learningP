package com.learning.service;

import com.learning.model.UserAttempt;

public interface UserAttemptService {

	UserAttempt findByEmail(String email);

	boolean existsByEmail(String email);

	void saveOrUpdate(UserAttempt userAttempt);

	boolean lock(String email);

	void unlock(String email);

}
