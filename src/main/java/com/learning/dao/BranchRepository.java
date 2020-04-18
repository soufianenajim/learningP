package com.learning.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Branch;

public interface BranchRepository extends JpaRepository<Branch,Long > {
	@Query("SELECT b FROM Branch b WHERE (LOWER(b.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Branch> findByName(String name,  Pageable pageable);
	
	@Query("SELECT b FROM Branch b WHERE (LOWER(b.name) LIKE CONCAT(lower(?1), '%')) and b.organization.id=?2 ")
	Page<Branch> findByNameAndOrganization(String code, Long idOrganization, Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("delete  FROM Branch  WHERE  organization.id=?1 ")
	void deleteByOrganisation(Long id);
	
	@Query("SELECT b FROM Branch b WHERE b.organization.id=?1 ")
	List<Branch> findByOrganization(Long id);
	
}
