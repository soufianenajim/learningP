package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.ProgressionModule;

public interface ProgressionModuleRepository extends JpaRepository<ProgressionModule, Long> {
	@Query("SELECT pm FROM ProgressionModule pm WHERE pm.student.id=?1 ")
	Page<ProgressionModule> findByUser(Long userId, Pageable pageable);

	@Query("SELECT pm FROM ProgressionModule pm WHERE pm.student.id=?1 and pm.module.id=?2 ")
	Page<ProgressionModule> findByUserAndModule(Long userId, Long moduleId, Pageable pageable);

}
