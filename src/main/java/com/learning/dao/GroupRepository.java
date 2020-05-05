package com.learning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

	@Query("select g from Group g where g.level.organization.id=?1")
	List<Group> findByOrganization(Long id);
	
	@Query("SELECT g FROM Group g WHERE LOWER(g.name) like lower(?1) and g.level.id=?2 and g.branch.id=?3 ")
	Group findByNameAndLevelAndBranch(String name,Long idLevel,Long idBranch);
}
