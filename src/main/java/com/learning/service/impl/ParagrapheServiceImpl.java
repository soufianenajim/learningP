package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.ParagrapheRepository;
import com.learning.dto.ParagrapheDTO;
import com.learning.model.Chapitre;
import com.learning.model.Paragraphe;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ChapitreService;
import com.learning.service.ParagrapheService;

@Service
public class ParagrapheServiceImpl implements ParagrapheService {
	@Autowired
	ParagrapheRepository paragrapheRepository;
	@Autowired
	ChapitreService chapitreService;

	@Override
	public ParagrapheDTO save(ParagrapheDTO paragrapheDTO) {
		Paragraphe paragraphe = convertDTOtoModel(paragrapheDTO);
		paragraphe = paragrapheRepository.save(paragraphe);
		return convertModelToDTO(paragraphe);
	}

	@Override
	public ParagrapheDTO findById(long idOut) {
		Optional<Paragraphe> optional = paragrapheRepository.findById(idOut);

		if (optional.isPresent()) {
			Paragraphe paragrapheFromDb = optional.get();
			return convertModelToDTO(paragrapheFromDb);
		}
		return null;
	}

	@Override
	public void delete(Paragraphe paragraphe) {
		paragrapheRepository.delete(paragraphe);
	}

	@Override
	public PartialList<ParagrapheDTO> findByCriteres(Demande<ParagrapheDTO> demande) {

		ParagrapheDTO paragraphe = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Paragraphe> pageParagraphe = paragrapheRepository.findByNameAndChapitre(paragraphe.getName(),
				paragraphe.getChapitre().getId(), PageRequest.of(page, size));

		List<ParagrapheDTO> list = convertEntitiesToDtos(pageParagraphe.getContent());
		int totalElement = pageParagraphe.getNumberOfElements();

		return new PartialList<ParagrapheDTO>(totalElement, list);
	}

	@Override
	public Paragraphe convertDTOtoModel(ParagrapheDTO paragrapheDTO) {
		Paragraphe paragraphe = new Paragraphe();
		paragraphe.setId(paragrapheDTO.getId());
		paragraphe.setName(paragrapheDTO.getName());

		if (paragrapheDTO.getChapitre() != null) {
			paragraphe.setChapitre(chapitreService.convertDTOtoModel(paragrapheDTO.getChapitre()));
		}
		return paragraphe;
	}

	@Override
	public ParagrapheDTO convertModelToDTO(Paragraphe paragraphe) {
		ParagrapheDTO paragrapheDTO = new ParagrapheDTO();
		paragrapheDTO.setId(paragraphe.getId());
		paragrapheDTO.setName(paragraphe.getName());
		Chapitre chapitre = paragraphe.getChapitre();
		if (chapitre != null) {
			paragrapheDTO.setChapitre(chapitreService.convertModelToDTO(chapitre));
		}
		paragrapheDTO.setCreatedAt(paragraphe.getCreatedAt());
		paragrapheDTO.setUpdatedAt(paragraphe.getUpdatedAt());
		return paragrapheDTO;
	}

	@Override
	public PartialList<ParagrapheDTO> convertToListDTO(PartialList<Paragraphe> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		paragrapheRepository.deleteById(id);

	}

	@Override
	public List<ParagrapheDTO> convertEntitiesToDtos(List<Paragraphe> paragraphes) {
		// basic methode
		List<ParagrapheDTO> list = new ArrayList<ParagrapheDTO>();
		for (Paragraphe paragraphe : paragraphes) {
			list.add(convertModelToDTO(paragraphe));
		}
		return list;
	}

	@Override
	public List<Paragraphe> convertDtosToEntities(List<ParagrapheDTO> paragraphesDTO) {
		List<Paragraphe> list = new ArrayList<Paragraphe>();
		for (ParagrapheDTO paragrapheDTO : paragraphesDTO) {
			list.add(convertDTOtoModel(paragrapheDTO));
		}
		return list;
	}

}
