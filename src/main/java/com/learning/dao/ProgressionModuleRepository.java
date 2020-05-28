package com.learning.dao;

import java.util.List;

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
	
	@Query("SELECT pm FROM ProgressionModule pm WHERE pm.module.id=?1 and pm.student.id=?2  ")
	ProgressionModule findByModuleAndStudent(Long moduleId,Long studentId);

	@Query("Select pm.module.name ,count(pm.id),sum(case when pm.noteFinal >= 50 then 1 else 0 end) from ProgressionModule pm where pm.module.professor.id=?1  group by pm.module.id")
	List<Object> countSuccessByTeacher(Long idTeacher);

	@Query("Select pm.module.name ,count(pm.id),sum(case when pm.noteFinal >= 50 then 1 else 0 end) from ProgressionModule pm where pm.module.professor.id=?1 and pm.module.group.id=?2 group by pm.module.id")
	List<Object> countSuccessByTeacherAndGroup(Long idTeacher, Long idGroup);
	
	@Query("Select pm.module.name ,count(pm.id),sum(case when pm.noteFinal >= 50 then 1 else 0 end) from ProgressionModule pm where pm.module.professor.id=?1 and pm.module.id=?2  group by pm.module.id")
	List<Object> countSuccessByTeacherAndModule(Long idTeacher, Long idModule);
	@Query("Select pm.module.name ,count(pm.id),sum(case when pm.noteFinal >= 50 then 1 else 0 end) from ProgressionModule pm where pm.module.professor.id=?1   and pm.module.group.id=?2 and pm.module.id=?3 group by pm.module.id")
	List<Object> countSuccessByTeacherAndGroupAndModule(Long idTeacher, Long idGroup,Long idModule);

}
