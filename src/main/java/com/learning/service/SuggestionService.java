package com.learning.service;

import java.util.List;

import com.learning.dto.SuggestionDTO;
import com.learning.model.Question;
import com.learning.model.Suggestion;

public interface SuggestionService extends CrudService<Suggestion, SuggestionDTO> {
	void saveSuggestionsByQuestion(List<SuggestionDTO> suggestions,Question question);
}
