package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.ModuleRepository;
import com.learning.dto.ModuleDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Group;
import com.learning.model.Module;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.GroupService;
import com.learning.service.ModuleService;
import com.learning.service.ProgressionModuleService;
import com.learning.service.UserService;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ProgressionModuleService progressionModuleService;

	@Override
	public ModuleDTO save(ModuleDTO moduleDTO) {
		Module module = convertDTOtoModel(moduleDTO);
		module = moduleRepository.save(module);
		// get student by level and branch
		if (moduleDTO.getId() == null) {
			List<UserDTO> students = userService.findByGroup(moduleDTO.getGroup().getId());
			// save progressionModule with module and students
			progressionModuleService.saveByModuleAndStudents(module, students);
		}

		return convertModelToDTO(module);
	}

	@Override
	public ModuleDTO findById(long idOut) {
		Optional<Module> optional = moduleRepository.findById(idOut);

		if (optional.isPresent()) {
			Module moduleFromDb = optional.get();
			return convertModelToDTO(moduleFromDb);
		}
		return null;
	}

	@Override
	public void delete(Module module) {
		moduleRepository.delete(module);
	}

	@Override
	public PartialList<ModuleDTO> findByCriteres(Demande<ModuleDTO> demande) {

		ModuleDTO module = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Module> pageModule = null;
		String name = module.getName();
		Long idProf = module.getProfessor() != null ? module.getProfessor().getId() : null;

		pageModule = idProf != null ? moduleRepository.findByNameAndUser(name, idProf, PageRequest.of(page, size))
				: moduleRepository.findByName(name, PageRequest.of(page, size));

		List<ModuleDTO> list = convertEntitiesToDtos(pageModule.getContent());
		Long totalElement = pageModule.getTotalElements();

		return new PartialList<ModuleDTO>(totalElement, list);
	}

	@Override
	public Module convertDTOtoModel(ModuleDTO moduleDTO) {
		Module module = new Module();
		module.setId(moduleDTO.getId());
		module.setName(moduleDTO.getName());

		if (moduleDTO.getProfessor() != null) {
			module.setProfessor(userService.convertDTOtoModel(moduleDTO.getProfessor()));
		}

		if (moduleDTO.getGroup() != null) {
			module.setGroup(groupService.convertDTOtoModel(moduleDTO.getGroup()));
		}
		
		return module;
	}

	@Override
	public ModuleDTO convertModelToDTO(Module module) {
		ModuleDTO moduleDTO = new ModuleDTO();
		moduleDTO.setId(module.getId());
		moduleDTO.setName(module.getName());
		User user = module.getProfessor();
		Group group = module.getGroup();
	
		if (user != null) {
			moduleDTO.setProfessor(userService.convertModelToDTO(module.getProfessor()));

		}
		if (group != null) {
			moduleDTO.setGroup(groupService.convertModelToDTO(group));
		}
		
		moduleDTO.setCreatedAt(module.getCreatedAt());
		moduleDTO.setUpdatedAt(module.getUpdatedAt());
		return moduleDTO;
	}

	@Override
	public PartialList<ModuleDTO> convertToListDTO(PartialList<Module> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		moduleRepository.deleteById(id);

	}

	@Override
	public List<ModuleDTO> convertEntitiesToDtos(List<Module> modules) {
		// basic methode
		List<ModuleDTO> list = new ArrayList<ModuleDTO>();
		for (Module module : modules) {
			list.add(convertModelToDTO(module));
		}
		return list;
	}

	@Override
	public List<Module> convertDtosToEntities(List<ModuleDTO> modulesDTO) {
		List<Module> list = new ArrayList<Module>();
		for (ModuleDTO moduleDTO : modulesDTO) {
			list.add(convertDTOtoModel(moduleDTO));
		}
		return list;
	}

	@Override
	public List<ModuleDTO> findAll() {
		List<Module> list = moduleRepository.findAll();
		return convertEntitiesToDtos(list);
	}

	@Override
	public Module convertDTOtoModelWithOutRelation(ModuleDTO moduleDTO) {
		Module module = new Module();
		module.setId(moduleDTO.getId());
		module.setName(moduleDTO.getName());

		return module;
	}

	@Override
	public ModuleDTO convertModelToDTOWithOutRelation(Module module) {
		ModuleDTO moduleDTO = new ModuleDTO();
		moduleDTO.setId(module.getId());
		moduleDTO.setName(module.getName());
		moduleDTO.setCreatedAt(module.getCreatedAt());
		moduleDTO.setUpdatedAt(module.getUpdatedAt());
		User professor=module.getProfessor();
		if(professor!=null) {
			moduleDTO.setProfessor(userService.convertModelToDTOWithOutRelation(professor));
		}
		return moduleDTO;
	}

	@Override
	public List<ModuleDTO> convertEntitiesToDtosWithOutRelation(List<Module> modules) {
		List<ModuleDTO> list = new ArrayList<ModuleDTO>();
		for (Module module : modules) {
			list.add(convertModelToDTOWithOutRelation(module));
		}
		return list;
	}

	@Override
	public List<Module> convertDtosToEntitiesWithOutRelation(List<ModuleDTO> modulesDTO) {
		List<Module> list = new ArrayList<Module>();
		for (ModuleDTO moduleDTO : modulesDTO) {
			list.add(convertDTOtoModelWithOutRelation(moduleDTO));
		}
		return list;
	}

	@Override
	public List<ModuleDTO> findByGroup(Long idGroup) {

		return convertEntitiesToDtosWithOutRelation(moduleRepository.findByGroup(idGroup));
	}

	@Override
	public List<ModuleDTO> findByProfessor(Long idProfessor) {
		
		return convertEntitiesToDtosWithOutRelation(moduleRepository.findByProfessor(idProfessor));
	}

}
