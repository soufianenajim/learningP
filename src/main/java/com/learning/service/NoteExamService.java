package com.learning.service;

import java.util.List;

import com.learning.dto.NoteExamDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Exam;
import com.learning.model.NoteExam;
import com.learning.model.TypeEnumExam;

public interface NoteExamService extends CrudService<NoteExam, NoteExamDTO> {
	
	void saveByExamAndStudent(Exam exam,List<UserDTO> students);
	
	List<Boolean> findStatutByUserAndModule(Long idUser,Long idModule);
	
	List<Double> findByUserAndModuleAndType(Long idUser,Long idModule,TypeEnumExam type);
}
