package com.learning.service;

import java.util.List;

import com.learning.dto.ChapitreDTO;
import com.learning.model.Chapitre;

public interface ChapitreService extends CrudService<Chapitre, ChapitreDTO> {
	List<ChapitreDTO> findAll();

}
