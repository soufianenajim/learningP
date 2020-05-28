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
import com.learning.model.Module;
import com.learning.model.Organization;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ModuleService;
import com.learning.service.OrganizationService;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private OrganizationService courService;

	// save or update
	@Override
	public ModuleDTO save(ModuleDTO moduleDTO) {
		if (moduleDTO.getId() != null) {
			if (!existingModuleById(moduleDTO.getId(), moduleDTO.getName(), moduleDTO.getOrganization().getId()))
				return null;
		} else if (!existingModule(moduleDTO.getName(), moduleDTO.getOrganization().getId()))
			return null;
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
		Long idOrganization = module.getOrganization() != null ? module.getOrganization().getId() : null;

		pageModule = idOrganization != null
				? moduleRepository.findByNameAndOrganization(name, idOrganization, PageRequest.of(page, size))
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

		if (moduleDTO.getOrganization() != null) {
			module.setOrganization(courService.convertDTOtoModel(moduleDTO.getOrganization()));
		}
		return module;
	}

	@Override
	public ModuleDTO convertModelToDTO(Module module) {
		ModuleDTO moduleDTO = new ModuleDTO();
		moduleDTO.setId(module.getId());
		moduleDTO.setName(module.getName());
		Organization cour = module.getOrganization();
		if (cour != null) {
			moduleDTO.setOrganization(courService.convertModelToDTO(module.getOrganization()));

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
	public Module convertDTOtoModelWithOutOrganization(ModuleDTO moduleDTO) {
		Module module = new Module();
		module.setId(moduleDTO.getId());
		module.setName(moduleDTO.getName());
		return module;
	}

	@Override
	public ModuleDTO convertModelToDTOWithOutOrganization(Module module) {
		ModuleDTO moduleDTO = new ModuleDTO();
		moduleDTO.setId(module.getId());
		moduleDTO.setName(module.getName());
		return moduleDTO;
	}

	@Override
	public List<ModuleDTO> convertEntitiesToDtosWithOutOrganization(List<Module> list) {
		List<ModuleDTO> modules = new ArrayList<>();
		for (Module module : list) {
			modules.add(convertModelToDTOWithOutOrganization(module));
		}
		return modules;
	}

	@Override
	public List<Module> convertDtosToEntitiesWithOutOrganization(List<ModuleDTO> list) {
		List<Module> modules = new ArrayList<>();
		for (ModuleDTO module : list) {
			modules.add(convertDTOtoModelWithOutOrganization(module));
		}
		return modules;
	}

	@Override
	public void saveModulesByOrganization(List<ModuleDTO> modules, Organization organization) {
		for (ModuleDTO moduleDTO : modules) {
			Module module = convertDTOtoModel(moduleDTO);
			module.setOrganization(organization);
			moduleRepository.save(module);

		}

	}

	@Override
	public void deleteByOrganizationId(Long id) {
		moduleRepository.deleteByOrganisation(id);

	}

	@Override
	public List<ModuleDTO> findByOrganization(Long id) {

		return convertEntitiesToDtosWithOutOrganization(moduleRepository.findByOrganization(id));
	}

	@Override
	public boolean existingModule(String name, Long idOrganization) {
		Module existModule = moduleRepository.findByNameAndOrganization(name, idOrganization);
		return existModule == null || existModule.getId() == null;
	}

	@Override
	public boolean existingModuleById(Long id, String name, Long idOrganization) {
		Module existModule = moduleRepository.findByNameAndOrganization(name, idOrganization);
		return existModule == null || existModule.getId().equals(id);
	}

}
