package com.learning.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Level;

public interface LevelRepository extends JpaRepository<Level,Long > {
	@Query("SELECT l FROM Level l WHERE (LOWER(l.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Level> findByName(String name,  Pageable pageable);
	
	@Query("SELECT l FROM Level l WHERE (LOWER(l.name) LIKE CONCAT(lower(?1), '%')) and l.organization.id=?2 ")
	Page<Level> findByNameAndOrganization(String code, Long idOrganization, Pageable pageable);
	

	@Modifying
	@Transactional
	@Query("delete  FROM Level  WHERE  organization.id=?1 ")
	void deleteByOrganisation(Long id);
}
