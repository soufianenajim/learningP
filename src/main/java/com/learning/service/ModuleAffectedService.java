package com.learning.service;

import java.util.List;

import com.learning.dto.ModuleAffectedDTO;
import com.learning.model.ModuleAffected;

public interface ModuleAffectedService extends CrudService<ModuleAffected, ModuleAffectedDTO> {
	List<ModuleAffectedDTO> findAll();

	ModuleAffected convertDTOtoModelWithOutRelation(ModuleAffectedDTO dto);

	ModuleAffectedDTO convertModelToDTOWithOutRelation(final ModuleAffected model);

	List<ModuleAffectedDTO> convertEntitiesToDtosWithOutRelation(List<ModuleAffected> list);

	List<ModuleAffected> convertDtosToEntitiesWithOutRelation(List<ModuleAffectedDTO> list);

	List<ModuleAffectedDTO> findByGroup(Long idGroup);

	List<ModuleAffectedDTO> findByProfessor(Long idProfessor);

	List<ModuleAffectedDTO> findByProfessorAndGroup(Long idProfessor, Long idGroup);

	boolean existingModule(String name, Long idProfessor, Long idGroup);

	boolean existingModuleById(Long id, String name, Long idProfessor, Long idGroup);

	Long getGroupByModule(Long idModule);

	void calculate(ModuleAffectedDTO module);

	Long countModuleByTeacherAndGroup(Long idTeacher, Long idGroup);

	Long countByOrganizationAndLevelAndBranch( Long idOrg,
			 Long idLevel,  Long idBranch,Long idGroup);
}
