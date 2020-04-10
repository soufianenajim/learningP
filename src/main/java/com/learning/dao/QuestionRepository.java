package com.learning.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	@Query("SELECT q FROM Question q WHERE (LOWER(q.code) LIKE CONCAT(?1, '%')) or (LOWER(q.name) LIKE CONCAT(?2, '%')) ")
	Page<Question> findByCodeAndName(String code, String name, Pageable pageable);

	@Query("SELECT q FROM Question q WHERE q.quiz.id=?1")
	List<Question> findByQuiz(Long quizId);

	@Query("SELECT q FROM Question q WHERE q.exam.id=?1")
	List<Question> findByExam(Long examId);
	
	@Query("SELECT q FROM Question q WHERE q.td.id=?1")
	List<Question> findByTd(Long tdId);

	@Modifying
	@Transactional
	@Query("update Question q set q.exam.id=null where q.exam.id=?1 ")
	void detacheExam(Long examId);
	
	@Modifying
	@Transactional
	@Query("update Question q set q.quiz.id=null where q.quiz.id=?1 ")
	void detacheQuiz(Long examId);
	
	@Modifying
	@Transactional
	@Query("update Question q set q.td.id=null where q.td.id=?1 ")
	void detacheTd(Long tdId);
}
