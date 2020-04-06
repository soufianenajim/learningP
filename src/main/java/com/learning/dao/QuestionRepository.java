package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	@Query("SELECT q FROM Question q WHERE (LOWER(q.code) LIKE CONCAT(?1, '%')) or (LOWER(q.name) LIKE CONCAT(?2, '%')) ")
	Page<Question> findByCodeAndName(String code, String name, Pageable pageable);

	@Query("SELECT q FROM Question q WHERE q.quiz.id=?1")
	List<Question> findByQuiz(Long quizId);
}
