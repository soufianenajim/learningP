package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.ModuleAffected;

public interface ModuleAffectedRepository extends JpaRepository<ModuleAffected, Long> {

	@Query("SELECT m FROM ModuleAffected m WHERE (LOWER(m.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<ModuleAffected> findByName(String name, Pageable pageable);

	@Query("SELECT m FROM ModuleAffected m WHERE (LOWER(m.name) LIKE CONCAT(lower(?1), '%')) and m.professor.id=?2 ")
	Page<ModuleAffected> findByNameAndUser(String name, Long idUser, Pageable pageable);

	@Query("SELECT m FROM ModuleAffected m WHERE m.group.id=?1  ")
	List<ModuleAffected> findByGroup(Long idGroup);

	@Query("SELECT m FROM ModuleAffected m WHERE m.professor.id=?1  ")
	List<ModuleAffected> findByProfessor(Long idProfessor);
	
	@Query("SELECT m FROM ModuleAffected m WHERE m.professor.id=?1 and m.group.id=?2  ")
	List<ModuleAffected> findByProfessorAndGroup(Long idProfessor,Long idGroup);
	
	@Query("SELECT m FROM ModuleAffected m WHERE LOWER(m.name) like lower(?1) and m.professor.id=?2 and m.group.id=?3 ")
	ModuleAffected findByNameAndProfessorAndGroup(String name, Long idProfessor, Long idGroup);
	
	@Query("SELECT m.group.id FROM ModuleAffected m WHERE m.id=?1 ")
	Long getGroupByModule(Long idModule);
	
	@Query("SELECT count(m.id) FROM ModuleAffected m WHERE m.professor.id=?1 ")
	Long countModuleByTeacher(Long idTeacher);
	
	@Query("SELECT count(m.id) FROM ModuleAffected m WHERE m.professor.id=?1 and m.group.id=?2 ")
	Long countModuleByTeacherAndGroup(Long idTeacher,Long idGroup);
}
