package com.learning.dao;
import java.util.List;

import com.learning.dto.ExercicesDTO;
import com.learning.model.Exercices;
import com.learning.model.base.Demande;

public interface ExercicesRepositorySearchCriteria {
	
	public List<Exercices> findByCriteres(Demande<ExercicesDTO> demande);
	
	public Long countByCriteres(Demande<ExercicesDTO> demande);


}