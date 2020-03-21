package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.NoteQuiz;

public interface NoteQuizRepository extends JpaRepository<NoteQuiz,Long > {
      
	@Query("SELECT nq FROM NoteQuiz nq WHERE nq.user.id = ?1 and nq.quiz.id = ?2")
	Page<NoteQuiz> findByUserAndQuiz(Long idUser, Long idQuiz, Pageable pageable);
}
 