package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Chapitre;

public interface ChapitreRepository extends JpaRepository<Chapitre,Long > {
	
	@Query("SELECT c FROM Chapitre c WHERE (LOWER(c.name) LIKE CONCAT(lower(?1), '%')) ")
	Page<Chapitre> findByName(String code,  Pageable pageable);
	
	@Query("SELECT c FROM Chapitre c WHERE (LOWER(c.name) LIKE CONCAT(lower(?1), '%')) and c.cour.id=?2 ")
	Page<Chapitre> findByNameAndCour(String code, Long idCour, Pageable pageable);
	
}
