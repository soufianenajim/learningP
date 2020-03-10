package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.NoteQuiz;

public interface NoteQuizRepository extends JpaRepository<NoteQuiz,Long > {

}
