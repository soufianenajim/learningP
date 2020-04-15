package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.ProgressionModuleRepository;
import com.learning.dto.ProgressionModuleDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Module;
import com.learning.model.ProgressionModule;
import com.learning.model.User;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ModuleService;
import com.learning.service.ProgressionModuleService;
import com.learning.service.UserService;

@Service
public class ProgressionModuleServiceImpl implements ProgressionModuleService {

	@Autowired
	private ProgressionModuleRepository progressionModuleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ModuleService moduleService;

	// save or update
	@Override
	public ProgressionModuleDTO save(ProgressionModuleDTO progressionModuleDTO) {
		ProgressionModule progressionModule = convertDTOtoModel(progressionModuleDTO);
		progressionModule = progressionModuleRepository.save(progressionModule);
		return convertModelToDTO(progressionModule);
	}

	@Override
	public ProgressionModuleDTO findById(long idOut) {
		Optional<ProgressionModule> optional = progressionModuleRepository.findById(idOut);

		if (optional.isPresent()) {
			ProgressionModule progressionModuleFromDb = optional.get();
			return convertModelToDTO(progressionModuleFromDb);
		}
		return null;
	}

	@Override
	public void delete(ProgressionModule progressionModule) {
		progressionModuleRepository.delete(progressionModule);
	}

	@Override
	public PartialList<ProgressionModuleDTO> findByCriteres(Demande<ProgressionModuleDTO> demande) {

		ProgressionModuleDTO progressionModule = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Long idUser = progressionModule.getStudent() != null ? progressionModule.getStudent().getId() : null;
		Long idModule = progressionModule.getModule() != null ? progressionModule.getModule().getId() : null;
		Page<ProgressionModule> pageProgressionModule = idUser != null
				? idModule != null
						? progressionModuleRepository.findByUserAndModule(idUser, idModule, PageRequest.of(page, size))
						: progressionModuleRepository.findByUser(idUser, PageRequest.of(page, size))
				: null;

		if (pageProgressionModule != null) {
			List<ProgressionModuleDTO> list = convertEntitiesToDtos(pageProgressionModule.getContent());
			Long totalElement = pageProgressionModule.getTotalElements();

			return new PartialList<ProgressionModuleDTO>(totalElement, list);
		} else {
			return new PartialList<ProgressionModuleDTO>(0l, new ArrayList<ProgressionModuleDTO>());
		}
	}

	@Override
	public ProgressionModule convertDTOtoModel(ProgressionModuleDTO progressionModuleDTO) {
		ProgressionModule progressionModule = new ProgressionModule();
		progressionModule.setId(progressionModuleDTO.getId());
		progressionModule.setProgressionCour(progressionModuleDTO.getProgressionCour());
		progressionModule.setProgressionExam(progressionModuleDTO.getProgressionExam());
		progressionModule.setNoteFinal(progressionModuleDTO.getNoteFinal());
		if (progressionModuleDTO.getModule() != null) {
			progressionModule
					.setModule(moduleService.convertDTOtoModelWithOutRelation(progressionModuleDTO.getModule()));
		}
		if (progressionModuleDTO.getStudent() != null) {
			progressionModule
					.setStudent(userService.convertDTOtoModelWithOutRelation(progressionModuleDTO.getStudent()));
		}

		return progressionModule;
	}

	@Override
	public ProgressionModuleDTO convertModelToDTO(ProgressionModule progressionModule) {
		ProgressionModuleDTO progressionModuleDTO = new ProgressionModuleDTO();
		progressionModuleDTO.setId(progressionModule.getId());
		progressionModuleDTO.setProgressionCour(progressionModule.getProgressionCour());
		progressionModuleDTO.setProgressionExam(progressionModule.getProgressionExam());
		progressionModuleDTO.setNoteFinal(progressionModule.getNoteFinal());
		Module module = progressionModule.getModule();
		User student = progressionModule.getStudent();
		if (module != null) {
			progressionModuleDTO.setModule(moduleService.convertModelToDTOWithOutRelation(module));
		}

		if (student != null) {
			progressionModuleDTO.setStudent(userService.convertModelToDTOWithOutRelation(student));
		}
		return progressionModuleDTO;
	}

	@Override
	public PartialList<ProgressionModuleDTO> convertToListDTO(PartialList<ProgressionModule> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		progressionModuleRepository.deleteById(id);

	}

	@Override
	public List<ProgressionModuleDTO> convertEntitiesToDtos(List<ProgressionModule> progressionModules) {
		// basic methode
		List<ProgressionModuleDTO> list = new ArrayList<ProgressionModuleDTO>();
		for (ProgressionModule progressionModule : progressionModules) {
			list.add(convertModelToDTO(progressionModule));
		}
		return list;
	}

	@Override
	public List<ProgressionModule> convertDtosToEntities(List<ProgressionModuleDTO> progressionModulesDTO) {
		List<ProgressionModule> list = new ArrayList<ProgressionModule>();
		for (ProgressionModuleDTO progressionModuleDTO : progressionModulesDTO) {
			list.add(convertDTOtoModel(progressionModuleDTO));
		}
		return list;
	}

	@Override
	public void saveByModuleAndStudents(Module module, List<UserDTO> students) {
		for (UserDTO student : students) {
			ProgressionModule progressionModule = new ProgressionModule();
			progressionModule.setModule(module);
			progressionModule.setStudent(userService.convertDTOtoModel(student));
			progressionModule.setProgressionCour(0.0);
			progressionModule.setProgressionExam(0.0);
			progressionModuleRepository.save(progressionModule);
		}

	}

}
