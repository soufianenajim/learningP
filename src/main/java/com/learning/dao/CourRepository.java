package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Cour;

public interface CourRepository extends JpaRepository<Cour,Long > {
	
	@Query("SELECT c FROM Cour c WHERE (LOWER(c.name) LIKE CONCAT(?1, '%')) and c.module.id = ?2")
	Page<Cour> findByNameAndModule(String name,Long idModule,Pageable pageable);
}
