package com.learning.service;

import java.util.List;

import com.learning.dto.ModuleAffectedDTO;
import com.learning.dto.ProgressionModuleDTO;
import com.learning.dto.UserDTO;
import com.learning.model.ModuleAffected;
import com.learning.model.ProgressionModule;
import com.learning.model.User;

public interface ProgressionModuleService extends CrudService<ProgressionModule, ProgressionModuleDTO> {
	void saveByModuleAndStudents(ModuleAffected module, List<UserDTO> students);

	void saveByStudentAndModules(User student, List<ModuleAffectedDTO> modules);

	void updateProgressionModule(Long idModule, Long idStudent);

	void calculateNoteFinal(Long idModule);

	List<Object> getAverageSuccessStudent(Long idTeacher, Long idGroup, Long idModule);
	
	List<Object> getAverageSuccessStudentByOrg(Long idOrg, Long idLevel, Long idBranch,Long idGroup);

	List<ProgressionModule> findByModuleAndSecondNotSuccess(Long idModule);

	Long countModuleByStudent(Long idStudent);
	
	List<ModuleAffectedDTO> getModuleByStudent(Long idStudent);
	
	
}
