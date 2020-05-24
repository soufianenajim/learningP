package com.learning.dao;
import java.util.List;

import com.learning.dto.CourDTO;
import com.learning.model.Cour;
import com.learning.model.base.Demande;

public interface CourRepositorySearchCriteria {
	
	public List<Cour> findByCriteres(Demande<CourDTO> demande);
	
	public Long countByCriteres(Demande<CourDTO> demande);


}