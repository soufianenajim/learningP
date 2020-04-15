package com.learning.service;

import java.util.List;

import com.learning.dto.ModuleDTO;
import com.learning.model.Module;

public interface ModuleService extends CrudService<Module, ModuleDTO> {
	List<ModuleDTO> findAll();
	
	Module convertDTOtoModelWithOutRelation(ModuleDTO dto);

	ModuleDTO convertModelToDTOWithOutRelation(final Module model);

	List<ModuleDTO> convertEntitiesToDtosWithOutRelation(List<Module> list);

	List<Module> convertDtosToEntitiesWithOutRelation(List<ModuleDTO> list);
}
