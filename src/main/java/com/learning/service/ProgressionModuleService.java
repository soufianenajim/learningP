package com.learning.service;

import java.util.List;

import com.learning.dto.ModuleDTO;
import com.learning.dto.ProgressionModuleDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Module;
import com.learning.model.ProgressionModule;
import com.learning.model.User;

public interface ProgressionModuleService extends CrudService<ProgressionModule, ProgressionModuleDTO> {
	void saveByModuleAndStudents(Module module, List<UserDTO> students);

	void saveByStudentAndModules(User student, List<ModuleDTO> modules);

	void updateProgressionModule(Long idModule, Long idStudent);
	
	
}
