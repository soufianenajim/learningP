package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.CourRepository;
import com.learning.dto.CourDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Cour;
import com.learning.model.Module;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;
import com.learning.service.ModuleService;
import com.learning.service.ProgressionCourService;
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

	// save or update
	@Override
	public CourDTO save(CourDTO courDTO) {
		Cour cour = convertDTOtoModel(courDTO);
		cour = courRepository.save(cour);
		Module module=cour.getModule();
		Long idLevel=module.getLevel().getId();
		Long idBranch=module.getBranch().getId();
		List<UserDTO> students =userService.findByLevelAndBranch(idLevel, idBranch);
		progressionCourService.saveByCourAndStudents(cour, students);
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

		CourDTO cour = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Cour> pageCour = null;
		String name = cour.getName();
		Long idModule = cour.getModule() != null ? cour.getModule().getId() : null;

		pageCour = idModule != null ? courRepository.findByNameAndModule(name, idModule, PageRequest.of(page, size))
				: courRepository.findByName(name, PageRequest.of(page, size));

		List<CourDTO> list = convertEntitiesToDtos(pageCour.getContent());
		Long totalElement = pageCour.getTotalElements();

		return new PartialList<CourDTO>(totalElement, list);
	}

	@Override
	public Cour convertDTOtoModel(CourDTO courDTO) {
		Cour cour = new Cour();
		cour.setId(courDTO.getId());
		cour.setName(courDTO.getName());
		cour.setContent(courDTO.getContent());
		if (courDTO.getModule() != null) {
			cour.setModule(moduleService.convertDTOtoModel(courDTO.getModule()));
		}

		return cour;
	}

	@Override
	public CourDTO convertModelToDTO(Cour cour) {
		CourDTO courDTO = new CourDTO();
		courDTO.setId(cour.getId());
		courDTO.setName(cour.getName());
		courDTO.setContent(cour.getContent());
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

}
