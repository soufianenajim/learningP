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
import com.learning.model.Branch;
import com.learning.model.Level;
import com.learning.model.Module;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.BranchService;
import com.learning.service.LevelService;
import com.learning.service.ModuleService;
import com.learning.service.UserService;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private LevelService levelService;

	@Override
	public ModuleDTO save(ModuleDTO moduleDTO) {
		Module module = convertDTOtoModel(moduleDTO);
		module = moduleRepository.save(module);
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
		Long idProf = module.getUser() != null ? module.getUser().getId() : null;

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

		if (moduleDTO.getUser() != null) {
			module.setUser(userService.convertDTOtoModel(moduleDTO.getUser()));
		}

		if (moduleDTO.getBranch() != null) {
			module.setBranch(branchService.convertDTOtoModel(moduleDTO.getBranch()));
		}
		if (moduleDTO.getLevel() != null) {
			module.setLevel(levelService.convertDTOtoModel(moduleDTO.getLevel()));
		}
		return module;
	}

	@Override
	public ModuleDTO convertModelToDTO(Module module) {
		ModuleDTO moduleDTO = new ModuleDTO();
		moduleDTO.setId(module.getId());
		moduleDTO.setName(module.getName());
		User user = module.getUser();
		Branch branch = module.getBranch();
		Level level = module.getLevel();
		if (user != null) {
			moduleDTO.setUser(userService.convertModelToDTO(module.getUser()));

		}
		if (branch != null) {
			moduleDTO.setBranch(branchService.convertModelToDTO(branch));
		}
		if (level != null) {
			moduleDTO.setLevel(levelService.convertModelToDTO(level));
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

		boolean exist = moduleRepository.existsById(id);
		if (exist)
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

}
