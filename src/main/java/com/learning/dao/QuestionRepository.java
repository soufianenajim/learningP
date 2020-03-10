package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Long > {

}
