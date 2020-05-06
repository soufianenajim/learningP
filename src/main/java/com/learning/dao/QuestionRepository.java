package com.learning.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Question;
import com.learning.model.TypeEnum;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	@Query("SELECT q FROM Question q WHERE (LOWER(q.code) LIKE CONCAT(?1, '%')) or (LOWER(q.name) LIKE CONCAT(?2, '%')) ")
	Page<Question> findByCodeAndName(String code, String name, Pageable pageable);

	@Query("SELECT q FROM Question q WHERE q.exercices.id=?1")
	List<Question> findByExercices(Long quizId);

	@Query("SELECT q FROM Question q WHERE q.exam.id=?1")
	List<Question> findByExam(Long examId);
	
	

	@Modifying
	@Transactional
	@Query("update Question q set q.exam.id=null where q.exam.id=?1 ")
	void detacheExam(Long examId);
	
	@Modifying
	@Transactional
	@Query("update Question q set q.exercices.id=null where q.exercices.id=?1 ")
	void detacheExercices(Long examId);
	
    
    @Query("SELECT q FROM Question q WHERE LOWER(q.name) like lower(?1) and q.exercices.type=?2 and q.exercices.id=?3 ")
   	Question findByNameAndTypeAndExercice(String name,TypeEnum type,Long idExercice);
    
    @Query("SELECT q FROM Question q WHERE LOWER(q.name) like lower(?1) and q.exam.id=?2 ")
	Question findByNameAndExam(String name,Long idExam);
	

}
