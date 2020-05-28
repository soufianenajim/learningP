package com.learning.service;

import java.util.List;

import com.learning.dto.ModuleDTO;
import com.learning.model.Module;
import com.learning.model.Organization;

public interface ModuleService extends CrudService<Module, ModuleDTO> {
	List<ModuleDTO> findAll();

	void saveModulesByOrganization(List<ModuleDTO> modules, Organization organization);

	Module convertDTOtoModelWithOutOrganization(ModuleDTO dto);

	ModuleDTO convertModelToDTOWithOutOrganization(final Module model);

	List<ModuleDTO> convertEntitiesToDtosWithOutOrganization(List<Module> list);

	List<Module> convertDtosToEntitiesWithOutOrganization(List<ModuleDTO> list);
	
   void deleteByOrganizationId(Long id);
   
   List<ModuleDTO> findByOrganization(Long id);
   
	boolean existingModule(String name, Long idOrganization);

	boolean existingModuleById(Long id, String name, Long idOrganization);
}
