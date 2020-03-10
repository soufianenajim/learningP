package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.NoteExam;

public interface NoteExamRepository extends JpaRepository<NoteExam,Long > {

}
