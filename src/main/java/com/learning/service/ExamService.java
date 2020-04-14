package com.learning.service;

import com.learning.dto.ExamDTO;
import com.learning.model.Exam;

public interface ExamService extends CrudService<Exam, ExamDTO> {

	void deleteByModule(Long idModule);
	
	
	
}
