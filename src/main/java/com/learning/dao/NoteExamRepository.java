package com.learning.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.NoteExam;

public interface NoteExamRepository extends JpaRepository<NoteExam, Long> {
	@Query("SELECT ne FROM NoteExam ne WHERE ne.user.id = ?1 and ne.exam.id = ?2")
	Page<NoteExam> findByUserAndExam(Long idUser, Long idExam, Pageable pageable);

}
