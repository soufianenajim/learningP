package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.ChapitreRepository;
import com.learning.dto.ChapitreDTO;
import com.learning.model.Chapitre;
import com.learning.model.Cour;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ChapitreService;
import com.learning.service.CourService;

@Service
public class ChapitreServiceImpl implements ChapitreService {

	@Autowired
	ChapitreRepository chapitreRepository;
	@Autowired
	CourService courService;

	// save or update
	@Override
	public Chapitre save(ChapitreDTO chapitreDTO) {
		Chapitre chapitre = convertDTOtoModel(chapitreDTO);
		return chapitreRepository.save(chapitre);
	}

	@Override
	public Chapitre findById(long idOut) {
		Optional<Chapitre> optional = chapitreRepository.findById(idOut);

		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void delete(Chapitre chapitre) {
		chapitreRepository.delete(chapitre);
	}

	@Override
	public PartialList<ChapitreDTO> findByCriteres(Demande<ChapitreDTO> demande) {

		ChapitreDTO chapitre = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Chapitre> pageChapitre = chapitreRepository.findByName(chapitre.getName(), PageRequest.of(page, size));

		List<ChapitreDTO> list = convertEntitiesToDtos(pageChapitre.getContent());
		int totalElement = pageChapitre.getNumberOfElements();

		return new PartialList<ChapitreDTO>(totalElement, list);
	}

	@Override
	public Chapitre convertDTOtoModel(ChapitreDTO chapitreDTO) {
		Chapitre chapitre = new Chapitre();
		chapitre.setId(chapitreDTO.getId());
		chapitre.setName(chapitreDTO.getName());
		if (chapitreDTO.getCour() != null) {
			chapitre.setCour(courService.convertDTOtoModel(chapitreDTO.getCour()));
		}
		return chapitre;
	}

	@Override
	public ChapitreDTO convertModelToDTO(Chapitre chapitre) {
		ChapitreDTO chapitreDTO = new ChapitreDTO();
		chapitreDTO.setId(chapitre.getId());
		chapitreDTO.setName(chapitre.getName());
		Cour cour = chapitre.getCour();
		if (cour != null) {
			chapitreDTO.setCour(courService.convertModelToDTO(chapitre.getCour()));
		}
		chapitreDTO.setCreatedAt(chapitre.getCreatedAt());
		chapitreDTO.setUpdatedAt(chapitre.getUpdatedAt());
		return chapitreDTO;
	}

	@Override
	public PartialList<ChapitreDTO> convertToListDTO(PartialList<Chapitre> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		chapitreRepository.deleteById(id);

	}

	@Override
	public List<ChapitreDTO> convertEntitiesToDtos(List<Chapitre> chapitres) {
		// basic methode
		List<ChapitreDTO> list = new ArrayList<ChapitreDTO>();
		for (Chapitre chapitre : chapitres) {
			list.add(convertModelToDTO(chapitre));
		}
		return list;
	}

	@Override
	public List<Chapitre> convertDtosToEntities(List<ChapitreDTO> chapitresDTO) {
		List<Chapitre> list = new ArrayList<Chapitre>();
		for (ChapitreDTO chapitreDTO : chapitresDTO) {
			list.add(convertDTOtoModel(chapitreDTO));
		}
		return list;
	}

}
