package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.ProgressionCour;

public interface ProgressionCourRepository extends JpaRepository<ProgressionCour, Long> {
	@Query("SELECT pc FROM ProgressionCour pc WHERE pc.student.id=?1 ")
	Page<ProgressionCour> findByStudent(Long userId, Pageable pageable);

	@Query("SELECT pc FROM ProgressionCour pc WHERE pc.student.id=?1 and pc.cour.id=?2 ")
	Page<ProgressionCour> findByStudentAndCour(Long userId, Long courId, Pageable pageable);

	@Query("SELECT pc FROM ProgressionCour pc WHERE pc.student.id=?1 and pc.cour.module.id=?2")
	Page<ProgressionCour> findByStudentAndModule(Long userId,Long moduleId, Pageable pageable);
	
	@Query("SELECT pc.progression FROM ProgressionCour pc WHERE pc.cour.module.id=?1 and  pc.student.id=?2")
	List<Double> listOfProgressionByModuleAndStudent(Long idModule,Long idStudent);
	
	@Query("SELECT count(distinct pc) FROM ProgressionCour pc WHERE pc.student.id=?1")
	Long countCourseByStudent(Long idStudent);
	
	@Query("SELECT count(distinct pc) FROM ProgressionCour pc WHERE pc.student.id=?1 and  pc.cour.module.id=?2")
	Long countCourseByModuleAndStudent(Long idStudent,Long idModule);
	
}

