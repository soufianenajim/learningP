package com.learning.dao;
import java.util.List;

import com.learning.dto.ProgressionModuleDTO;
import com.learning.model.ProgressionModule;
import com.learning.model.base.Demande;

public interface ProgressionModuleRepositorySearchCriteria {
	
	public List<ProgressionModule> findByCriteres(Demande<ProgressionModuleDTO> demande);
	
	public Long countByCriteres(Demande<ProgressionModuleDTO> demande);


}