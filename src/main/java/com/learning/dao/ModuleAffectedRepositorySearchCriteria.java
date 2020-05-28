package com.learning.dao;
import java.util.List;

import com.learning.dto.ModuleAffectedDTO;
import com.learning.model.ModuleAffected;
import com.learning.model.base.Demande;

public interface ModuleAffectedRepositorySearchCriteria {
	
	public List<ModuleAffected> findByCriteres(Demande<ModuleAffectedDTO> demande);
	
	public Long countByCriteres(Demande<ModuleAffectedDTO> demande);


}