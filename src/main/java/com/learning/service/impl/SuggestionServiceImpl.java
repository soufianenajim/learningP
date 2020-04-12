package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.SuggestionRepository;
import com.learning.dto.SuggestionDTO;
import com.learning.model.Question;
import com.learning.model.Suggestion;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.QuestionService;
import com.learning.service.SuggestionService;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	@Autowired
	private SuggestionRepository suggestionRepository;
	@Autowired
	private QuestionService questionService;

	@Override
	public SuggestionDTO save(SuggestionDTO suggestionDTO) {
		Suggestion suggestion = convertDTOtoModel(suggestionDTO);
		suggestion = suggestionRepository.save(suggestion);
		return convertModelToDTO(suggestion);
	}

	@Override
	public SuggestionDTO findById(long idOut) {
		Optional<Suggestion> optional = suggestionRepository.findById(idOut);

		if (optional.isPresent()) {
			Suggestion suggestionFromDb = optional.get();
			return convertModelToDTO(suggestionFromDb);
		}
		return null;
	}

	@Override
	public void delete(Suggestion suggestion) {
		suggestionRepository.delete(suggestion);
	}

	@Override
	public PartialList<SuggestionDTO> findByCriteres(Demande<SuggestionDTO> demande) {

		SuggestionDTO suggestion = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Suggestion> pageSuggestion = null;

		pageSuggestion = suggestionRepository.findByNameAndQuestion(suggestion.getName(),
				suggestion.getQuestion().getId(), PageRequest.of(page, size));

		List<SuggestionDTO> list = convertEntitiesToDtos(pageSuggestion.getContent());
		Long totalElement = pageSuggestion.getTotalElements();

		return new PartialList<SuggestionDTO>(totalElement, list);
	}

	@Override
	public Suggestion convertDTOtoModel(SuggestionDTO suggestionDTO) {
		Suggestion suggestion = new Suggestion();
		suggestion.setId(suggestionDTO.getId());
		suggestion.setName(suggestionDTO.getName());
		suggestion.setCorrect(suggestionDTO.isCorrect());
		if (suggestionDTO.getQuestion() != null) {
			suggestion.setQuestion(questionService.convertDTOtoModel(suggestionDTO.getQuestion()));
		}
		return suggestion;
	}

	@Override
	public SuggestionDTO convertModelToDTO(Suggestion suggestion) {
		SuggestionDTO suggestionDTO = new SuggestionDTO();
		suggestionDTO.setId(suggestion.getId());
		suggestionDTO.setName(suggestion.getName());
		suggestionDTO.setCorrect(suggestion.isCorrect());
//		Question question = suggestion.getQuestion();
//
//		if (question != null) {
//			suggestionDTO.setQuestion(questionService.convertModelToDTO(question));
//		}

		suggestionDTO.setCreatedAt(suggestion.getCreatedAt());
		suggestionDTO.setUpdatedAt(suggestion.getUpdatedAt());
		return suggestionDTO;
	}

	@Override
	public PartialList<SuggestionDTO> convertToListDTO(PartialList<Suggestion> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		suggestionRepository.deleteById(id);

	}

	@Override
	public List<SuggestionDTO> convertEntitiesToDtos(List<Suggestion> suggestions) {
		// basic methode
		List<SuggestionDTO> list = new ArrayList<SuggestionDTO>();
		for (Suggestion suggestion : suggestions) {
			list.add(convertModelToDTO(suggestion));
		}
		return list;
	}

	@Override
	public List<Suggestion> convertDtosToEntities(List<SuggestionDTO> suggestionsDTO) {
		List<Suggestion> list = new ArrayList<Suggestion>();
		for (SuggestionDTO suggestionDTO : suggestionsDTO) {
			list.add(convertDTOtoModel(suggestionDTO));
		}
		return list;
	}

	@Override
	public void saveSuggestionsByQuestion(List<SuggestionDTO> suggestions, Question question) {
		for (SuggestionDTO suggestionDTO : suggestions) {
			Suggestion suggestion = convertDTOtoModel(suggestionDTO);
			suggestion.setQuestion(question);
			suggestionRepository.save(suggestion);

		}
		
	}

}
