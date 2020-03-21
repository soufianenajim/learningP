package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Long > {
	@Query("SELECT q FROM Question q WHERE (LOWER(q.code) LIKE CONCAT(?1, '%')) and p.td.id = ?2")
	Page<Question> findByCodeAndTd(String code,Long idTd,Pageable pageable);
	
	@Query("SELECT q FROM Question q WHERE (LOWER(q.code) LIKE CONCAT(?1, '%')) and p.quiz.id = ?2")
	Page<Question> findByCodeAndQuiz(String code,Long idQuiz,Pageable pageable);
	
	@Query("SELECT q FROM Question q WHERE (LOWER(q.code) LIKE CONCAT(?1, '%')) and p.exam.id = ?2")
	Page<Question> findByCodeAndExam(String code,Long idExam,Pageable pageable);
}
