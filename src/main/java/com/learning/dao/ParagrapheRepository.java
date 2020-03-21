package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Paragraphe;

public interface ParagrapheRepository extends JpaRepository<Paragraphe,Long > {
	
	@Query("SELECT p FROM Paragraphe p WHERE (LOWER(p.name) LIKE CONCAT(?1, '%')) and p.chapitre.id = ?2")
	Page<Paragraphe> findByNameAndChapitre(String name,Long idChapitre,Pageable pageable);
}
