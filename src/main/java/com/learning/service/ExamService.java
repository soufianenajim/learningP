package com.learning.service;

import java.util.List;

import com.learning.dto.ExamDTO;
import com.learning.model.Exam;

public interface ExamService extends CrudService<Exam, ExamDTO> {

	void deleteByModule(Long idModule);
	
	ExamDTO convertModelToDTOWithoutQuestion(Exam exam);
	List<ExamDTO> findByModule(Long idModule);
	
}
