package com.learning.service;

import java.util.List;

import com.learning.dto.LevelDTO;
import com.learning.model.Level;
import com.learning.model.Organization;

public interface LevelService extends CrudService<Level, LevelDTO> {
	List<LevelDTO> findAll();
	

	void saveLevelsByOrganization(List<LevelDTO> levels, Organization organization);

	Level convertDTOtoModelWithOutOrganization(LevelDTO dto);

	LevelDTO convertModelToDTOWithOutOrganization(final Level model);

	List<LevelDTO> convertEntitiesToDtosWithOutOrganization(List<Level> list);

	List<Level> convertDtosToEntitiesWithOutOrganization(List<LevelDTO> list);
	
   void deleteByOrganizationId(Long id);
	
	

}
