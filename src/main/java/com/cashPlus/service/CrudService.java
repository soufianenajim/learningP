package com.cashPlus.service;

import org.springframework.data.domain.Pageable;

import com.cashPlus.model.base.PartialList;

public interface CrudService<T, U> {
	T save(T t);

	T findById(long idOut);

	void delete(T t);

	PartialList<U> findByCriteres(Pageable page, String name);

	T convertDTOtoModel(U u);

	U convertModelToDTO(final T t);

	PartialList<U> convertToListDTO(PartialList<T> list);

}
