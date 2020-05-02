package com.learning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.RoleName;
import com.learning.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

//	@Query("select u from User u where u.level.id=?1 and u.branch.id=?2")
//	List<User> findByLevelAndBranch(Long idLevel,Long idBranch);

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	@Query("select u from User u where u.refRole.name=?1 and u.organization.id=?2")
	List<User> findByRoleAndOrganization(RoleName roleName, Long idOrganization);

	@Query("select u from User u JOIN u.groups ug where ug.id=?1 ")
	List<User> findByGroup(Long idGroup);

}
