package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Exam;

public interface ExamRepository extends JpaRepository<Exam,Long > {
	
}
