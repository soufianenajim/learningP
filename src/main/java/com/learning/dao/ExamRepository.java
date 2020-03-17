package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Exam;

public interface ExamRepository extends JpaRepository<Exam,Long > {
	Page<Exam> findByName(String name,Pageable pageable);
}
