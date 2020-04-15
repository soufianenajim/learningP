package com.learning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.User;

public interface UserRepository extends JpaRepository<User,Long > {

	@Query("select u from User u where u.level.id=?1 and u.branch.id=?2")
	List<User> findByLevelAndBranch(Long idLevel,Long idBranch);
}
