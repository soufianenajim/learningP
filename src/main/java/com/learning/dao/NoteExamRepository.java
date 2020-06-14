package com.learning.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.NoteExam;
import com.learning.model.TypeEnumExam;

public interface NoteExamRepository extends JpaRepository<NoteExam, Long> {
	@Query("SELECT ne FROM NoteExam ne WHERE ne.user.id = ?1 and ne.exam.id = ?2")
	Page<NoteExam> findByUserAndExam(Long idUser, Long idExam, Pageable pageable);

	@Query("Select ne.finished from NoteExam ne where ne.user.id=?1 and ne.exam.module.id=?2")
	List<Boolean> findStatutByUserAndModule(Long idUser, Long idModule);

	@Query("Select ne.score from NoteExam ne where ne.user.id=?1 and ne.exam.module.id=?2 and ne.exam.type=?3")
	List<Double> findByUserAndModuleAndType(Long idUser, Long idModule, TypeEnumExam type);

	@Query("Select ne.exam.name ,count(ne.id),sum(case when ne.score >= 50 then 1 else 0 end) from NoteExam ne where ne.exam.module.professor.id=?1  group by ne.exam.id")
	List<Object> countSuccessByTeacher(Long idTeacher);

	@Query("Select ne.exam.name ,count(ne.id),sum(case when ne.score >= 50 then 1 else 0 end) from NoteExam ne where ne.exam.module.professor.id=?1 and ne.exam.module.group.id=?2 group by ne.exam.id")
	List<Object> countSuccessByTeacherAndGroup(Long idTeacher, Long idGroup);

	@Query("Select ne.exam.name ,count(ne.id),sum(case when ne.score >= 50 then 1 else 0 end) from NoteExam ne where ne.exam.module.professor.id=?1 and ne.exam.module.id=?2 group by ne.exam.id")
	List<Object> countSuccessByTeacherAndModule(Long idTeacher, Long idModule);

	@Query("Select ne.exam.name ,count(ne.id),sum(case when ne.score >= 50 then 1 else 0 end) from NoteExam ne where ne.exam.module.professor.id=?1 and ne.exam.module.group.id=?2 and ne.exam.module.id=?3 group by ne.exam.id")
	List<Object> countSuccessByTeacherAndGroupAndModule(Long idTeacher, Long idGroup, Long idModule);

	@Query("Select count(distinct ne) from NoteExam ne where ne.user.id=?1 and ne.exam.type=?2")
	Long countExamByStudentAndType(Long idStudent, TypeEnumExam type);
	
	@Query("Select count(distinct ne) from NoteExam ne where ne.user.id=?1 and ne.exam.module.id=?2 and ne.exam.type=?3")
	Long countExamByStudentAndModuleType(Long idStudent,Long idModule, TypeEnumExam type);
}
