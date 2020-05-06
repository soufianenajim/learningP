package com.learning.service;

import java.util.List;

import com.learning.dto.CourDTO;
import com.learning.model.Cour;

public interface CourService extends CrudService<Cour, CourDTO> {
	Cour findEnitityById(Long id);

	List<CourDTO> findAll();

	void deleteByModule(Long idModule);

	Cour convertDTOtoModelWithOutModule(CourDTO courDTO);

	CourDTO convertModelToDTOWithOutModule(final Cour cour);

	List<CourDTO> convertEntitiesToDtosWithOutModule(List<Cour> list);

	List<Cour> convertDtosToEntitiesWithOutModule(List<CourDTO> list);

	List<CourDTO> findByModule(Long idModule);
	
	List<CourDTO> findByModuleAndLaunched(Long idModule,boolean isLaunched);

	void launch(Long idCour);
	
	boolean existingCour(String name, Long idModule);

	boolean existingCourById(Long id, String name, Long idModule);
}
