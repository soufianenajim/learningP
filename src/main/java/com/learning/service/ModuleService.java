package com.learning.service;

import java.util.List;

import com.learning.dto.ModuleDTO;
import com.learning.model.Module;

public interface ModuleService extends CrudService<Module, ModuleDTO> {
	List<ModuleDTO> findAll();
}
