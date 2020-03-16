package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.ParagrapheDTO;
import com.learning.model.Paragraphe;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ParagrapheService;

@Service
public class ParagrapheServiceImpl implements ParagrapheService {

	@Override
	public ParagrapheDTO save(ParagrapheDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParagrapheDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Paragraphe t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<ParagrapheDTO> findByCriteres(Demande<ParagrapheDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paragraphe convertDTOtoModel(ParagrapheDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParagrapheDTO convertModelToDTO(Paragraphe t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<ParagrapheDTO> convertToListDTO(PartialList<Paragraphe> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParagrapheDTO> convertEntitiesToDtos(List<Paragraphe> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paragraphe> convertDtosToEntities(List<ParagrapheDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
