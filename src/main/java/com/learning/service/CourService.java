package com.learning.service;

import java.util.List;

import com.learning.dto.CourDTO;
import com.learning.model.Cour;

public interface CourService extends CrudService<Cour, CourDTO> {
	Cour findEnitityById(Long id);
	List<CourDTO> findAll();
}
