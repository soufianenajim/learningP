package com.learning.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
	@Query("SELECT b FROM Module b WHERE (LOWER(b.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Module> findByName(String name, Pageable pageable);

	@Query("SELECT b FROM Module b WHERE (LOWER(b.name) LIKE CONCAT(lower(?1), '%')) and b.organization.id=?2 ")
	Page<Module> findByNameAndOrganization(String code, Long idOrganization, Pageable pageable);

	@Modifying
	@Transactional
	@Query("delete  FROM Module  WHERE  organization.id=?1 ")
	void deleteByOrganisation(Long id);

	@Query("SELECT b FROM Module b WHERE b.organization.id=?1 ")
	List<Module> findByOrganization(Long id);

	@Query("SELECT l FROM Module l WHERE LOWER(l.name) like lower(?1) and l.organization.id=?2 ")
	Module findByNameAndOrganization(String name, Long idOrganization);

}
