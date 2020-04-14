package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.LevelRepository;
import com.learning.dto.LevelDTO;
import com.learning.model.Level;
import com.learning.model.Organization;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.LevelService;
import com.learning.service.OrganizationService;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	private LevelRepository levelRepository;
	
	@Autowired
	private OrganizationService courService;

	// save or update
	@Override
	public LevelDTO save(LevelDTO levelDTO) {
		Level level = convertDTOtoModel(levelDTO);
		level = levelRepository.save(level);
		return convertModelToDTO(level);
	}
	

	@Override
	public LevelDTO findById(long idOut) {
		Optional<Level> optional = levelRepository.findById(idOut);

		if (optional.isPresent()) {
			Level levelFromDb = optional.get();
			return convertModelToDTO(levelFromDb);
		}
		return null;
	}

	@Override
	public void delete(Level level) {
		levelRepository.delete(level);
	}

	@Override
	public PartialList<LevelDTO> findByCriteres(Demande<LevelDTO> demande) {

		LevelDTO level = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Level> pageLevel = null;
		String name = level.getName();
		Long idOrganization = level.getOrganization() != null ? level.getOrganization().getId() : null;

		pageLevel = idOrganization != null ? levelRepository.findByNameAndOrganization(name, idOrganization, PageRequest.of(page, size))
				: levelRepository.findByName(name, PageRequest.of(page, size));

		List<LevelDTO> list = convertEntitiesToDtos(pageLevel.getContent());
		Long totalElement = pageLevel.getTotalElements();
		return new PartialList<LevelDTO>(totalElement, list);
	}

	@Override
	public Level convertDTOtoModel(LevelDTO levelDTO) {
		Level level = new Level();
		level.setId(levelDTO.getId());
		level.setName(levelDTO.getName());

		if (levelDTO.getOrganization() != null) {
			level.setOrganization(courService.convertDTOtoModel(levelDTO.getOrganization()));
		}
		return level;
	}

	@Override
	public LevelDTO convertModelToDTO(Level level) {
		LevelDTO levelDTO = new LevelDTO();
		levelDTO.setId(level.getId());
		levelDTO.setName(level.getName());
		Organization cour = level.getOrganization();
		if (cour != null) {
			levelDTO.setOrganization(courService.convertModelToDTO(level.getOrganization()));
			
		}
		
		levelDTO.setCreatedAt(level.getCreatedAt());
		levelDTO.setUpdatedAt(level.getUpdatedAt());
		return levelDTO;
	}

	@Override
	public PartialList<LevelDTO> convertToListDTO(PartialList<Level> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		levelRepository.deleteById(id);

	}

	@Override
	public List<LevelDTO> convertEntitiesToDtos(List<Level> levels) {
		// basic methode
		List<LevelDTO> list = new ArrayList<LevelDTO>();
		for (Level level : levels) {
			list.add(convertModelToDTO(level));
		}
		return list;
	}

	@Override
	public List<Level> convertDtosToEntities(List<LevelDTO> levelsDTO) {
		List<Level> list = new ArrayList<Level>();
		for (LevelDTO levelDTO : levelsDTO) {
			list.add(convertDTOtoModel(levelDTO));
		}
		return list;
	}

	@Override
	public List<LevelDTO> findAll() {
		List<Level> list = levelRepository.findAll();
		return convertEntitiesToDtos(list);
	}

	@Override
	public Level convertDTOtoModelWithOutOrganization(LevelDTO levelDTO) {
		Level level = new Level();
		level.setId(levelDTO.getId());
		level.setName(levelDTO.getName());
		return level;
	}

	@Override
	public LevelDTO convertModelToDTOWithOutOrganization(Level level) {
		LevelDTO levelDTO = new LevelDTO();
		levelDTO.setId(level.getId());
		levelDTO.setName(level.getName());
		return levelDTO;
	}

	@Override
	public List<LevelDTO> convertEntitiesToDtosWithOutOrganization(List<Level> list) {
		List<LevelDTO> levels = new ArrayList<>();
		for (Level level : list) {
			levels.add(convertModelToDTOWithOutOrganization(level));
		}
		return levels;
	}

	@Override
	public List<Level> convertDtosToEntitiesWithOutOrganization(List<LevelDTO> list) {
		List<Level> levels = new ArrayList<>();
		for (LevelDTO level : list) {
			levels.add(convertDTOtoModelWithOutOrganization(level));
		}
		return levels;
	}

	@Override
	public void saveLevelsByOrganization(List<LevelDTO> levels, Organization organization) {
		for (LevelDTO levelDTO : levels) {
			Level level = convertDTOtoModel(levelDTO);
			level.setOrganization(organization);
			levelRepository.save(level);

		}

	}

	@Override
	public void deleteByOrganizationId(Long id) {
	levelRepository.deleteByOrganisation(id);
		
	}

	
}
