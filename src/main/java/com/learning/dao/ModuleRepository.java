package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

	@Query("SELECT m FROM Module m WHERE (LOWER(m.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Module> findByName(String name, Pageable pageable);

	@Query("SELECT m FROM Module m WHERE (LOWER(m.name) LIKE CONCAT(lower(?1), '%')) and m.professor.id=?2 ")
	Page<Module> findByNameAndUser(String name, Long idUser, Pageable pageable);

	@Query("SELECT m FROM Module m WHERE m.level.id=?1 and m.branch.id=?2 ")
	List<Module> findByLevelAndBranch(Long idLevel, Long idBranch);
}
