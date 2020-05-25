package com.learning.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Cour;

public interface CourRepository extends JpaRepository<Cour, Long> {

	@Query("SELECT c FROM Cour c WHERE (LOWER(c.name) LIKE CONCAT(?1, '%')) and c.module.id = ?2")
	Page<Cour> findByNameAndModule(String name, Long idModule, Pageable pageable);

	@Query("SELECT c FROM Cour c WHERE (LOWER(c.name) LIKE CONCAT(?1, '%'))")
	Page<Cour> findByName(String name, Pageable pageable);

	@Modifying
	@Transactional
	@Query("delete  FROM Cour  WHERE  module.id=?1 ")
	void deleteByModule(Long id);

	@Query("SELECT c FROM Cour c WHERE  c.module.id = ?1")
	List<Cour> findByModule(Long idModule);
	
	@Query("SELECT c FROM Cour c WHERE  c.module.id = ?1 and c.isLaunched is false")
	List<Cour> findByModuleAndNotLaunched(Long idModule);
	
	@Query("SELECT c FROM Cour c WHERE  c.module.id = ?1 and c.isLaunched=?2")
	List<Cour> findByModuleAndLaunched(Long idModule, boolean launched);

	@Query("SELECT c FROM Cour c WHERE LOWER(c.name) like lower(?1) and c.module.id=?2 ")
	Cour findByNameAndModule(String name, Long idModule);
	
	@Query("SELECT count(c.id) FROM Cour c where c.module.professor.id=?1")
	Long countCourByTeacher(Long idTeacher);
	@Query("SELECT count(c.id) FROM Cour c where c.module.professor.id=?1 and c.module.group.id=?2")
	Long countCourByTeacherAndGroup(Long idTeacher,Long idGroup);

}
