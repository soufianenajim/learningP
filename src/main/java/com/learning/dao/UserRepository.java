package com.learning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Group;
import com.learning.model.RoleName;
import com.learning.model.StatutEnum;
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

	@Query("select u from User u JOIN u.groups ug where ug.id=?1 and u.refRole.name=?2 ")
	List<User> findByGroupAndRole(Long idGroup, RoleName roleName);

	@Query("select distinct u from User u,NoteExam ne where ne.user.id=u.id and ne.exam.id =?2 and( (LOWER(u.firstName) LIKE CONCAT(lower(?1), '%')) or (LOWER(u.lastName) LIKE CONCAT(lower(?1), '%')) or (LOWER(u.email) LIKE CONCAT(lower(?1), '%')))")
	List<User> findByNameContainingByExam(String name, Long idExam);

	@Query("select count(u.id) FROM User u JOIN u.groups ug where ug in ?1 and   u.refRole.name=?2 ")
	Long countStudentByTeacher(List<Group> groups,RoleName roleName);
	
	
	@Query("select count(u.id) from User u JOIN u.groups ug where ug.id=?1 and  u.refRole.name=?2 ")
	Long countByGroupAndType(Long idGroup,RoleName roleName);
	
	@Query("select distinct u from  User  u,ProgressionModule pm where u.id=pm.student.id and pm.module.id=?1 and pm.statut=?2")
	List<User> findCatchingUpStudentByModule(Long idModule,StatutEnum statut);
	
	

}
