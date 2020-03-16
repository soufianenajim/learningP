package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.SuggestionDTO;
import com.learning.model.Suggestion;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.SuggestionService;

@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Override
	public Suggestion save(SuggestionDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuggestionDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Suggestion t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<SuggestionDTO> findByCriteres(Demande<SuggestionDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Suggestion convertDTOtoModel(SuggestionDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuggestionDTO convertModelToDTO(Suggestion t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<SuggestionDTO> convertToListDTO(PartialList<Suggestion> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SuggestionDTO> convertEntitiesToDtos(List<Suggestion> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Suggestion> convertDtosToEntities(List<SuggestionDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
