package com.learning.service;

import java.util.List;

import com.learning.dto.NoteExamDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Exam;
import com.learning.model.NoteExam;

public interface NoteExamService extends CrudService<NoteExam, NoteExamDTO> {
	
	void saveByExamAndStudent(Exam exam,List<UserDTO> students);
}
