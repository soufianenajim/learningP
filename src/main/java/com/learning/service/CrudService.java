package com.learning.service;

import java.util.List;

import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;

public interface CrudService<T, U> {
	T save(U u);

	T findById(long idOut);

	void delete(T t);

	PartialList<U> findByCriteres(Demande<U> demande);

	T convertDTOtoModel(U u);

	U convertModelToDTO(final T t);

	PartialList<U> convertToListDTO(PartialList<T> list);
	

	List<U> convertEntitiesToDtos(List<T> list);
	List<T> convertDtosToEntities(List<U> list);

}
