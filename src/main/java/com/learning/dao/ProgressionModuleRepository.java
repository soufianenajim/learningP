package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.ModuleAffected;
import com.learning.model.ProgressionModule;

public interface ProgressionModuleRepository extends JpaRepository<ProgressionModule, Long> {
	@Query("SELECT pm FROM ProgressionModule pm WHERE pm.student.id=?1 ")
	Page<ProgressionModule> findByUser(Long userId, Pageable pageable);

	@Query("SELECT pm FROM ProgressionModule pm WHERE pm.student.id=?1 and pm.module.id=?2 ")
	Page<ProgressionModule> findByUserAndModule(Long userId, Long moduleId, Pageable pageable);

	@Query("SELECT pm FROM ProgressionModule pm WHERE pm.module.id=?1 and pm.student.id=?2  ")
	ProgressionModule findByModuleAndStudent(Long moduleId, Long studentId);

	@Query("Select pm.statut ,count(pm.id) from ProgressionModule pm where pm.module.professor.id=?1 and pm.module.session.id=?2  group by pm.statut")
	List<Object> countSuccessByTeacher(Long idTeacher, Long idSession);

	@Query("Select pm.statut,count(pm.id) from ProgressionModule pm where pm.module.professor.id=?1 and pm.module.group.id=?2 and pm.module.session.id=?3 group by pm.statut")
	List<Object> countSuccessByTeacherAndGroup(Long idTeacher, Long idGroup, Long idSession);

	@Query("Select pm.statut ,count(pm.id) from ProgressionModule pm where pm.module.professor.id=?1 and pm.module.id=?2 and pm.module.session.id=?3 group by pm.statut")
	List<Object> countSuccessByTeacherAndModule(Long idTeacher, Long idModule, Long idSession);

	@Query("Select pm.statut ,count(pm.id)from ProgressionModule pm where pm.module.professor.id=?1   and pm.module.group.id=?2 and pm.module.id=?3 and  pm.module.session.id=?4 group by pm.statut")
	List<Object> countSuccessByTeacherAndGroupAndModule(Long idTeacher, Long idGroup, Long idModule, Long idSession);

	@Query("Select pm.statut ,count(pm.id) from ProgressionModule pm where pm.module.professor.organization.id=?1 and pm.module.session.id=?2  group by pm.statut")
	List<Object> countSuccessByOrganization(Long idOrg, Long idSession);
	
	@Query("Select pm.statut ,count(pm.id) from ProgressionModule pm ,User u JOIN u.groups ug where pm.student.id=u.id and  ug.level.id=?1 and pm.module.session.id=?2  group by pm.statut")
	List<Object> countSuccessByLevel(Long idLevel, Long idSession);
	
	@Query("Select pm.statut ,count(pm.id) from ProgressionModule pm ,User u JOIN u.groups ug where pm.student.id=u.id and  ug.branch.id=?1 and pm.module.session.id=?2  group by pm.statut")
	List<Object> countSuccessByBranch(Long idBranch, Long idSession);
	
	@Query("Select pm.statut ,count(pm.id) from ProgressionModule pm ,User u JOIN u.groups ug where pm.student.id=u.id and  ug.level.id=?1 and  ug.branch.id=?2 and pm.module.session.id=?3  group by pm.statut")
	List<Object> countSuccessByLevelAndBranch(Long idLevel,Long idBranch, Long idSession);
	
	@Query("Select pm.statut ,count(pm.id) from ProgressionModule pm ,User u JOIN u.groups ug where pm.student.id=u.id and  ug.id=?1 and pm.module.session.id=?2  group by pm.statut")
	List<Object> countSuccessByGroup(Long idTeacher, Long idSession);
	
	@Query("Select pm from ProgressionModule pm where pm.module.id=?1  and pm.secondSuccess is false")
	List<ProgressionModule> findByModuleAndSecondNotSuccess(Long idModule);
	
	

	@Query("SELECT count(distinct pm) FROM ProgressionModule pm WHERE  pm.student.id=?1  ")
	Long countModuleByStudent(Long idStudent);
	
	@Query("SELECT pm.module FROM ProgressionModule pm WHERE  pm.student.id=?1  ")
	List<ModuleAffected> getModuleByStudent(Long idStudent);
}
