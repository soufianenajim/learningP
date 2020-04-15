package com.learning.service;

import java.util.List;

import com.learning.dto.ProgressionModuleDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Module;
import com.learning.model.ProgressionModule;

public interface ProgressionModuleService extends CrudService<ProgressionModule, ProgressionModuleDTO> {
	void saveByModuleAndStudents(Module module,List<UserDTO> students);
}
