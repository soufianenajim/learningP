package com.learning.service;

import java.util.List;

import com.learning.dto.LevelDTO;
import com.learning.model.Level;

public interface LevelService extends CrudService<Level, LevelDTO> {
	List<LevelDTO> findAll();
	
	

}
