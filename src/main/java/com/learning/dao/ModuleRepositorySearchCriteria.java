package com.learning.dao;
import java.util.List;

import com.learning.dto.ModuleDTO;
import com.learning.model.Module;
import com.learning.model.base.Demande;

public interface ModuleRepositorySearchCriteria {
	
	public List<Module> findByCriteres(Demande<ModuleDTO> demande);
	
	public Long countByCriteres(Demande<ModuleDTO> demande);


}