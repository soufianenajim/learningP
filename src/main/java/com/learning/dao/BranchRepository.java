package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Branch;

public interface BranchRepository extends JpaRepository<Branch,Long > {
	@Query("SELECT b FROM Branch b WHERE (LOWER(b.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Branch> findByName(String name,  Pageable pageable);
	
	@Query("SELECT b FROM Branch b WHERE (LOWER(b.name) LIKE CONCAT(lower(?1), '%')) and b.organization.id=?2 ")
	Page<Branch> findByNameAndOrganization(String code, Long idOrganization, Pageable pageable);
	
}
