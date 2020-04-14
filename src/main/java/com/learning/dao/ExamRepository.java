package com.learning.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Exam;

public interface ExamRepository extends JpaRepository<Exam,Long > {
	@Query("SELECT e FROM Exam e WHERE (LOWER(e.name) LIKE CONCAT(?1, '%')) and e.module.id = ?2")
	Page<Exam> findByNameAndModule(String code,Long idModule,Pageable pageable);
	
	@Query("SELECT e FROM Exam e WHERE (LOWER(e.name) LIKE CONCAT(?1, '%'))")
	Page<Exam> findByName(String name,Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("delete  FROM Exam  WHERE  module.id=?1 ")
	void deleteByModule(Long id);
}
