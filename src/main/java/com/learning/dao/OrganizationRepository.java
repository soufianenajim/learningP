package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization,Long > {
	
	@Query("SELECT o FROM Organization o WHERE (LOWER(o.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Organization> findByName(String name,  Pageable pageable);

	@Query("select distinct o from Organization o,User u ,ModuleAffected m where m.professor.id=u.id and u.organization.id=o.id")
	Organization findByModule(Long idModule);
	
}

