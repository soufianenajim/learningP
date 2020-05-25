package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.CourRepository;
import com.learning.dao.CourRepositorySearchCriteria;
import com.learning.dto.CourDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Cour;
import com.learning.model.Exercices;
import com.learning.model.Module;
import com.learning.model.RoleName;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;
import com.learning.service.ExercicesService;
import com.learning.service.ModuleService;
import com.learning.service.ProgressionCourService;
import com.learning.service.ProgressionModuleService;
import com.learning.service.UserService;

@Service
public class CourServiceImpl implements CourService {

	@Autowired
	private CourRepository courRepository;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProgressionCourService progressionCourService;

	@Autowired
	private ExercicesService exercicesService;

	@Autowired
	private ProgressionModuleService progressionModuleService;
	@Autowired
	private CourRepositorySearchCriteria courRepositorySearchCriteria;

	// save or update
	@Override
	public CourDTO save(CourDTO courDTO) {
		if (courDTO.getId() != null) {
			if (!existingCourById(courDTO.getId(), courDTO.getName(), courDTO.getModule().getId()))
				return null;
		} else if (!existingCour(courDTO.getName(), courDTO.getModule().getId()))
			return null;
		Cour cour = convertDTOtoModel(courDTO);
		cour = courRepository.save(cour);

		return convertModelToDTO(cour);
	}

	@Override
	public CourDTO findById(long idOut) {
		Optional<Cour> optional = courRepository.findById(idOut);

		if (optional.isPresent()) {
			Cour courFromDb = optional.get();
			return convertModelToDTO(courFromDb);
		}
		return null;
	}

	@Override
	public void delete(Cour cour) {
		courRepository.delete(cour);
	}

	@Override
	public PartialList<CourDTO> findByCriteres(Demande<CourDTO> demande) {

		List<Cour> cours = courRepositorySearchCriteria.findByCriteres(demande);
		Long count = courRepositorySearchCriteria.countByCriteres(demande);
		return new PartialList<CourDTO>(count, convertEntitiesToDtos(cours));

	}

	@Override
	public Cour convertDTOtoModel(CourDTO courDTO) {
		Cour cour = new Cour();
		cour.setId(courDTO.getId());
		cour.setName(courDTO.getName());
		cour.setContent(courDTO.getContent());
		cour.setLaunched(courDTO.isLaunched());
		if (courDTO.getModule() != null) {
			cour.setModule(moduleService.convertDTOtoModel(courDTO.getModule()));
		}

		if (courDTO.getExercices() != null) {
			cour.setExercices(exercicesService.convertDtosToEntities(courDTO.getExercices()));
		}

		return cour;
	}

	@Override
	public CourDTO convertModelToDTO(Cour cour) {
		CourDTO courDTO = new CourDTO();
		courDTO.setId(cour.getId());
		courDTO.setName(cour.getName());
		courDTO.setContent(cour.getContent());
		courDTO.setLaunched(cour.isLaunched());
		Module module = cour.getModule();

		if (module != null) {
			courDTO.setModule(moduleService.convertModelToDTO(cour.getModule()));

		}

		courDTO.setCreatedAt(cour.getCreatedAt());
		courDTO.setUpdatedAt(cour.getUpdatedAt());
		return courDTO;
	}

	@Override
	public PartialList<CourDTO> convertToListDTO(PartialList<Cour> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		courRepository.deleteById(id);

	}

	@Override
	public List<CourDTO> convertEntitiesToDtos(List<Cour> cours) {
		// basic methode
		List<CourDTO> list = new ArrayList<CourDTO>();
		for (Cour cour : cours) {
			list.add(convertModelToDTO(cour));
		}
		return list;
	}

	@Override
	public List<Cour> convertDtosToEntities(List<CourDTO> coursDTO) {
		List<Cour> list = new ArrayList<Cour>();
		for (CourDTO courDTO : coursDTO) {
			list.add(convertDTOtoModel(courDTO));
		}
		return list;
	}

	@Override
	public Cour findEnitityById(Long id) {
		Optional<Cour> optional = courRepository.findById(id);

		if (optional.isPresent()) {
			Cour courFromDb = optional.get();
			return courFromDb;
		}
		return null;
	}

	@Override
	public List<CourDTO> findAll() {
		List<Cour> cours = courRepository.findAll();
		return convertEntitiesToDtos(cours);
	}

	@Override
	public void deleteByModule(Long idModule) {
		courRepository.deleteByModule(idModule);

	}

	@Override
	public Cour convertDTOtoModelWithOutModule(CourDTO courDTO) {
		Cour cour = new Cour();
		cour.setId(courDTO.getId());
		cour.setName(courDTO.getName());
		cour.setContent(courDTO.getContent());

		return cour;
	}

	@Override
	public CourDTO convertModelToDTOWithOutModule(Cour cour) {
		CourDTO courDTO = new CourDTO();
		courDTO.setId(cour.getId());
		courDTO.setName(cour.getName());
		courDTO.setContent(cour.getContent());
		List<Exercices> exercices = cour.getExercices();
		if (exercices != null) {
			courDTO.setExercices(exercicesService.convertEntitiesToDtos(exercices));
		}
		courDTO.setCreatedAt(cour.getCreatedAt());
		courDTO.setUpdatedAt(cour.getUpdatedAt());
		return courDTO;
	}

	@Override
	public List<CourDTO> convertEntitiesToDtosWithOutModule(List<Cour> cours) {
		List<CourDTO> list = new ArrayList<CourDTO>();
		for (Cour cour : cours) {
			list.add(convertModelToDTOWithOutModule(cour));
		}
		return list;
	}

	@Override
	public List<Cour> convertDtosToEntitiesWithOutModule(List<CourDTO> coursDTO) {
		List<Cour> list = new ArrayList<Cour>();
		for (CourDTO courDTO : coursDTO) {
			list.add(convertDTOtoModelWithOutModule(courDTO));
		}
		return list;
	}

	@Override
	public List<CourDTO> findByModule(Long idModule) {
		List<Cour> list = courRepository.findByModule(idModule);

		return convertEntitiesToDtosWithOutModule(list);
	}

	@Override
	public void launch(Long idCour) {
		Optional<Cour> optional = courRepository.findById(idCour);
		if (optional.isPresent()) {
			Cour cour = optional.get();
			cour.setLaunched(true);
			courRepository.save(cour);
			Module module = cour.getModule();
			Long idGroup = module.getGroup().getId();
			List<UserDTO> students = userService.findByGroupAndRole(idGroup, RoleName.ROLE_STUDENT);
			progressionCourService.saveByCourAndStudents(cour, students);
			if (module.getId() != null && !module.isLaunched()) {
				module.setLaunched(true);
				moduleService.save(moduleService.convertModelToDTO(module));
				progressionModuleService.saveByModuleAndStudents(module, students);
			}
		}

	}

	@Override
	public List<CourDTO> findByModuleAndLaunched(Long idModule, boolean isLaunched) {
		List<Cour> list = courRepository.findByModuleAndLaunched(idModule, isLaunched);

		return convertEntitiesToDtosWithOutModule(list);
	}

	@Override
	public boolean existingCour(String name, Long idModule) {
		Cour existCour = courRepository.findByNameAndModule(name, idModule);
		return existCour == null || existCour.getId() == null;
	}

	@Override
	public boolean existingCourById(Long id, String name, Long idModule) {
		Cour existCour = courRepository.findByNameAndModule(name, idModule);
		return existCour == null || existCour.getId().equals(id);
	}

	@Override
	public List<CourDTO> findByModuleAndNotLaunched(Long idModule) {

		return convertEntitiesToDtos(courRepository.findByModuleAndNotLaunched(idModule));
	}

	@Override
	public Long countCourByTeacherAndGroup(Long idTeacher, Long idGroup) {

		return idGroup > 0 ? courRepository.countCourByTeacherAndGroup(idTeacher, idGroup)
				: courRepository.countCourByTeacher(idTeacher);
	}

}
