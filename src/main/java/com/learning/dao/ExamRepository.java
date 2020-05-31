package com.learning.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Exam;
import com.learning.model.TypeEnumExam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
	@Query("SELECT e FROM Exam e WHERE (LOWER(e.name) LIKE CONCAT(?1, '%')) and e.module.id = ?2")
	Page<Exam> findByNameAndModule(String code, Long idModule, Pageable pageable);

	@Query("SELECT e FROM Exam e WHERE (LOWER(e.name) LIKE CONCAT(?1, '%'))")
	Page<Exam> findByName(String name, Pageable pageable);

	@Modifying
	@Transactional
	@Query("delete  FROM Exam  WHERE  module.id=?1 ")
	void deleteByModule(Long id);

	@Query("SELECT e FROM Exam e WHERE  e.module.id = ?1")
	List<Exam> findByModule(Long idModule);

	@Query("SELECT count(e.id) FROM Exam e WHERE  e.module.id = ?1")
	Long countExamByModule(Long idModule);

	@Query("SELECT distinct e FROM Exam e,NoteExam ne WHERE ne.exam.id=e.id and e.startDateTime>=?1 and ne.user.id=?2 ")
	List<Exam> findByUser(LocalDateTime localDateTime,Long idUser);

	@Query("SELECT count(e.id) from Exam e where e.module.professor.id=?1 and e.type=?2 ")
	Long countExamByTeacherAndType(Long idTeacher, TypeEnumExam type);

	@Query("SELECT count(e.id) from Exam e where e.module.professor.id=?1 and e.module.group.id=?2 and  e.type=?3 ")
	Long countExamByTeacherAndGroupAndType(Long idTeacher,Long idGroup, TypeEnumExam type);
}
