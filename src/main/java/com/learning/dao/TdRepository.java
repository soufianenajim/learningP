package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Td;

public interface TdRepository extends JpaRepository<Td, Long> {
	
	@Query("SELECT t FROM Td t WHERE (LOWER(t.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Td> findByName(String code,  Pageable pageable);
	
	@Query("SELECT t FROM Td t WHERE (LOWER(t.name) LIKE CONCAT(lower(?1), '%')) and t.cour.id=?2 ")
	Page<Td> findByNameAndCour(String code, Long idCour, Pageable pageable);
}
