package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long > {
	@Query("SELECT q FROM Quiz q WHERE (LOWER(q.name) LIKE CONCAT(?1, '%')) and q.cour.id = ?2")
	Page<Quiz> findByNameAndCour(String code,Long idCour,Pageable pageable);
	
	@Query("SELECT q FROM Quiz q WHERE (LOWER(q.name) LIKE CONCAT(?1, '%')) ")
	Page<Quiz> findByName(String code,Pageable pageable);
}
