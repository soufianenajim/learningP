package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long > {

}
