package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long > {
	@Query("SELECT q FROM Quiz q WHERE (LOWER(q.name) LIKE CONCAT(?1, '%')) and q.module.id = ?2")
	Page<Quiz> findByNameAndModule(String code,Long idModule,Pageable pageable);
	
	@Query("SELECT q FROM Quiz q WHERE (LOWER(q.name) LIKE CONCAT(?1, '%')) ")
	Page<Quiz> findByName(String code,Pageable pageable);
	
	@Query("SELECT q FROM Quiz q WHERE q.module.id=?1 ")
	List<Quiz> findByModule(Long idModule);
	
}
