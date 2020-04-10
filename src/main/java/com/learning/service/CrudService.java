package com.learning.service;

import java.util.List;

import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;

public interface CrudService<Model, ModelDTO> {
	
	ModelDTO save(ModelDTO dto);

	ModelDTO findById(long id);

	void delete(Model model);

	void deleteById(Long id);

	PartialList<ModelDTO> findByCriteres(Demande<ModelDTO> demande);

	Model convertDTOtoModel(ModelDTO dto);

	ModelDTO convertModelToDTO(final Model model);

	PartialList<ModelDTO> convertToListDTO(PartialList<Model> list);

	List<ModelDTO> convertEntitiesToDtos(List<Model> list);

	List<Model> convertDtosToEntities(List<ModelDTO> list);
	

}
