package com.learning.service;

import com.learning.dto.ChapitreDTO;
import com.learning.model.Chapitre;
import java.util.List;

public interface ChapitreService extends CrudService<Chapitre, ChapitreDTO> {
	void deleteById(Long id);
	List<ChapitreDTO> convertEntitiesToDtos(List<Chapitre> chapitres);
	List<Chapitre> convertDtosToEntities(List<ChapitreDTO> chapitres);

}
