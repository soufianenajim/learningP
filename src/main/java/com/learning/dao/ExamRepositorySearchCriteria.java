package com.learning.dao;
import java.util.List;

import com.learning.dto.ExamDTO;
import com.learning.model.Exam;
import com.learning.model.base.Demande;

public interface ExamRepositorySearchCriteria {
	
	public List<Exam> findByCriteres(Demande<ExamDTO> demande);
	
	public Long countByCriteres(Demande<ExamDTO> demande);


}