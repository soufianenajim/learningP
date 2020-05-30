package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.ModuleAffectedRepository;
import com.learning.dao.ModuleAffectedRepositorySearchCriteria;
import com.learning.dto.ModuleAffectedDTO;
import com.learning.model.Group;
import com.learning.model.ModuleAffected;
import com.learning.model.Session;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.GroupService;
import com.learning.service.ModuleAffectedService;
import com.learning.service.ModuleService;
import com.learning.service.ProgressionModuleService;
import com.learning.service.SessionService;
import com.learning.service.UserService;

@Service
public class ModuleAffectedServiceImpl implements ModuleAffectedService {

	@Autowired
	private ModuleAffectedRepository moduleAffectedRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;

	@Autowired
	private ModuleAffectedRepositorySearchCriteria moduleAffectedRepositorySearchCriteria;

	@Autowired
	private ProgressionModuleService progressionModuleService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private SessionService sessionService;

	@Override
	public ModuleAffectedDTO save(ModuleAffectedDTO moduleAffectedDTO) {

		if (moduleAffectedDTO.getId() != null) {
			if (!existingModuleById(moduleAffectedDTO.getId(), moduleAffectedDTO.getName(),
					moduleAffectedDTO.getProfessor().getId(), moduleAffectedDTO.getGroup().getId()))
				return null;
		} else if (!existingModule(moduleAffectedDTO.getName(), moduleAffectedDTO.getProfessor().getId(),
				moduleAffectedDTO.getGroup().getId()))
			return null;

		ModuleAffected moduleAffected = convertDTOtoModel(moduleAffectedDTO);
		moduleAffected = moduleAffectedRepository.save(moduleAffected);
		// get student by level and branch
		return convertModelToDTO(moduleAffected);
	}

	@Override
	public ModuleAffectedDTO findById(long idOut) {
		Optional<ModuleAffected> optional = moduleAffectedRepository.findById(idOut);

		if (optional.isPresent()) {
			ModuleAffected moduleAffectedFromDb = optional.get();
			return convertModelToDTO(moduleAffectedFromDb);
		}
		return null;
	}

	@Override
	public void delete(ModuleAffected moduleAffected) {
		moduleAffectedRepository.delete(moduleAffected);
	}

	@Override
	public PartialList<ModuleAffectedDTO> findByCriteres(Demande<ModuleAffectedDTO> demande) {

		List<ModuleAffected> moduleAffecteds = moduleAffectedRepositorySearchCriteria.findByCriteres(demande);
		Long count = moduleAffectedRepositorySearchCriteria.countByCriteres(demande);
		return new PartialList<ModuleAffectedDTO>(count, convertEntitiesToDtos(moduleAffecteds));
	}

	@Override
	public ModuleAffected convertDTOtoModel(ModuleAffectedDTO moduleAffectedDTO) {
		ModuleAffected moduleAffected = new ModuleAffected();
		moduleAffected.setId(moduleAffectedDTO.getId());
		moduleAffected.setName(moduleAffectedDTO.getName());
		moduleAffected.setLaunched(moduleAffectedDTO.isLaunched());
		moduleAffected.setCoefficient(moduleAffectedDTO.getCoefficient());
		moduleAffected.setPercentageAbsence(moduleAffectedDTO.getPercentageAbsence());
		moduleAffected.setPercentageCour(moduleAffectedDTO.getPercentageCour());
		moduleAffected.setPercentageExam(moduleAffectedDTO.getPercentageExam());
		moduleAffected.setPercentageQuiz(moduleAffectedDTO.getPercentageQuiz());
		moduleAffected.setScale(moduleAffectedDTO.getScale());

		if (moduleAffectedDTO.getProfessor() != null) {
			moduleAffected.setProfessor(userService.convertDTOtoModel(moduleAffectedDTO.getProfessor()));
		}

		if (moduleAffectedDTO.getGroup() != null) {
			moduleAffected.setGroup(groupService.convertDTOtoModel(moduleAffectedDTO.getGroup()));
		}
		if (moduleAffectedDTO.getModule() != null) {
			moduleAffected.setModule(moduleService.convertDTOtoModel(moduleAffectedDTO.getModule()));
		}
		if (moduleAffectedDTO.getSession() != null) {
			moduleAffected.setSession(sessionService.convertDTOtoModel(moduleAffectedDTO.getSession()));
		}
		return moduleAffected;
	}

	@Override
	public ModuleAffectedDTO convertModelToDTO(ModuleAffected moduleAffected) {
		ModuleAffectedDTO moduleAffectedDTO = new ModuleAffectedDTO();
		moduleAffectedDTO.setId(moduleAffected.getId());
		moduleAffectedDTO.setName(moduleAffected.getName());
		moduleAffectedDTO.setLaunched(moduleAffected.isLaunched());
		moduleAffectedDTO.setCoefficient(moduleAffected.getCoefficient());
		moduleAffectedDTO.setPercentageAbsence(moduleAffected.getPercentageAbsence());
		moduleAffectedDTO.setPercentageCour(moduleAffected.getPercentageCour());
		moduleAffectedDTO.setPercentageExam(moduleAffected.getPercentageExam());
		moduleAffectedDTO.setPercentageQuiz(moduleAffected.getPercentageQuiz());
		moduleAffectedDTO.setScale(moduleAffected.getScale());
		User user = moduleAffected.getProfessor();
		Group group = moduleAffected.getGroup();
		com.learning.model.Module module = moduleAffected.getModule();
		Session session = moduleAffected.getSession();

		if (user != null) {
			moduleAffectedDTO.setProfessor(userService.convertModelToDTO(moduleAffected.getProfessor()));

		}
		if (group != null) {
			moduleAffectedDTO.setGroup(groupService.convertModelToDTO(group));
		}
		if (module != null) {
			moduleAffectedDTO.setModule(moduleService.convertModelToDTO(module));
		}
		if (session != null) {

			moduleAffectedDTO.setSession(sessionService.convertModelToDTO(session));
		}
		moduleAffectedDTO.setCreatedAt(moduleAffected.getCreatedAt());
		moduleAffectedDTO.setUpdatedAt(moduleAffected.getUpdatedAt());
		return moduleAffectedDTO;
	}

	@Override
	public PartialList<ModuleAffectedDTO> convertToListDTO(PartialList<ModuleAffected> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		moduleAffectedRepository.deleteById(id);

	}

	@Override
	public List<ModuleAffectedDTO> convertEntitiesToDtos(List<ModuleAffected> moduleAffecteds) {
		// basic methode
		List<ModuleAffectedDTO> list = new ArrayList<ModuleAffectedDTO>();
		for (ModuleAffected moduleAffected : moduleAffecteds) {
			list.add(convertModelToDTO(moduleAffected));
		}
		return list;
	}

	@Override
	public List<ModuleAffected> convertDtosToEntities(List<ModuleAffectedDTO> moduleAffectedsDTO) {
		List<ModuleAffected> list = new ArrayList<ModuleAffected>();
		for (ModuleAffectedDTO moduleAffectedDTO : moduleAffectedsDTO) {
			list.add(convertDTOtoModel(moduleAffectedDTO));
		}
		return list;
	}

	@Override
	public List<ModuleAffectedDTO> findAll() {
		List<ModuleAffected> list = moduleAffectedRepository.findAll();
		return convertEntitiesToDtos(list);
	}

	@Override
	public ModuleAffected convertDTOtoModelWithOutRelation(ModuleAffectedDTO moduleAffectedDTO) {
		ModuleAffected moduleAffected = new ModuleAffected();
		moduleAffected.setId(moduleAffectedDTO.getId());
		moduleAffected.setName(moduleAffectedDTO.getName());

		return moduleAffected;
	}

	@Override
	public ModuleAffectedDTO convertModelToDTOWithOutRelation(ModuleAffected moduleAffected) {
		ModuleAffectedDTO moduleAffectedDTO = new ModuleAffectedDTO();
		moduleAffectedDTO.setId(moduleAffected.getId());
		moduleAffectedDTO.setName(moduleAffected.getName());
		moduleAffectedDTO.setCreatedAt(moduleAffected.getCreatedAt());
		moduleAffectedDTO.setUpdatedAt(moduleAffected.getUpdatedAt());
		moduleAffectedDTO.setLaunched(moduleAffected.isLaunched());
		moduleAffectedDTO.setCoefficient(moduleAffected.getCoefficient());
		moduleAffectedDTO.setPercentageAbsence(moduleAffected.getPercentageAbsence());
		moduleAffectedDTO.setPercentageCour(moduleAffected.getPercentageCour());
		moduleAffectedDTO.setPercentageExam(moduleAffected.getPercentageExam());
		moduleAffectedDTO.setPercentageQuiz(moduleAffected.getPercentageQuiz());
		moduleAffectedDTO.setScale(moduleAffected.getScale());
		User professor = moduleAffected.getProfessor();
		if (professor != null) {
			moduleAffectedDTO.setProfessor(userService.convertModelToDTOWithOutRelation(professor));
		}
		return moduleAffectedDTO;
	}

	@Override
	public List<ModuleAffectedDTO> convertEntitiesToDtosWithOutRelation(List<ModuleAffected> moduleAffecteds) {
		List<ModuleAffectedDTO> list = new ArrayList<ModuleAffectedDTO>();
		for (ModuleAffected moduleAffected : moduleAffecteds) {
			list.add(convertModelToDTOWithOutRelation(moduleAffected));
		}
		return list;
	}

	@Override
	public List<ModuleAffected> convertDtosToEntitiesWithOutRelation(List<ModuleAffectedDTO> moduleAffectedsDTO) {
		List<ModuleAffected> list = new ArrayList<ModuleAffected>();
		for (ModuleAffectedDTO moduleAffectedDTO : moduleAffectedsDTO) {
			list.add(convertDTOtoModelWithOutRelation(moduleAffectedDTO));
		}
		return list;
	}

	@Override
	public List<ModuleAffectedDTO> findByGroup(Long idGroup) {

		return convertEntitiesToDtosWithOutRelation(moduleAffectedRepository.findByGroup(idGroup));
	}

	@Override
	public List<ModuleAffectedDTO> findByProfessor(Long idProfessor) {

		return convertEntitiesToDtosWithOutRelation(moduleAffectedRepository.findByProfessor(idProfessor));
	}

	@Override
	public boolean existingModule(String name, Long idProfessor, Long idGroup) {
		ModuleAffected existModule = moduleAffectedRepository.findByNameAndProfessorAndGroup(name, idProfessor,
				idGroup);
		return existModule == null || existModule.getId() == null;
	}

	@Override
	public boolean existingModuleById(Long id, String name, Long idProfessor, Long idGroup) {
		ModuleAffected existModule = moduleAffectedRepository.findByNameAndProfessorAndGroup(name, idProfessor,
				idGroup);
		return existModule == null || existModule.getId().equals(id);
	}

	@Override
	public Long getGroupByModule(Long idModule) {

		return moduleAffectedRepository.getGroupByModule(idModule);
	}

	@Override
	public void calculate(ModuleAffectedDTO moduleAffected) {
	
		progressionModuleService.calculateNoteFinal(moduleAffected.getId());

	}

	@Override
	public Long countModuleByTeacherAndGroup(Long idTeacher, Long idGroup) {

		return idGroup > 0 ? moduleAffectedRepository.countModuleByTeacherAndGroup(idTeacher, idGroup)
				: moduleAffectedRepository.countModuleByTeacher(idTeacher);
	}

	@Override
	public List<ModuleAffectedDTO> findByProfessorAndGroup(Long idProfessor, Long idGroup) {

		return convertEntitiesToDtos(moduleAffectedRepository.findByProfessorAndGroup(idProfessor, idGroup));
	}

}
