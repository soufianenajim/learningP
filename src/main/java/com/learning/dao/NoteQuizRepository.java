package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.NoteQuiz;

public interface NoteQuizRepository extends JpaRepository<NoteQuiz,Long > {
      
	Page<NoteQuiz> findByScore(String score,Pageable pageable);
}
