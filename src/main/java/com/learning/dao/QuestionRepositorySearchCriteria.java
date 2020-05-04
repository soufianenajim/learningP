package com.learning.dao;
import java.util.List;

import com.learning.dto.QuestionDTO;
import com.learning.model.Question;
import com.learning.model.base.Demande;

public interface QuestionRepositorySearchCriteria {
	
	public List<Question> findByCriteres(Demande<QuestionDTO> demande);
	
	public Long countByCriteres(Demande<QuestionDTO> demande);


}