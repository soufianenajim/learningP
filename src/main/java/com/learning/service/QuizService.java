package com.learning.service;

import java.util.List;

import com.learning.dto.QuizDTO;
import com.learning.model.Quiz;

public interface QuizService extends CrudService<Quiz, QuizDTO> {
	
	List<QuizDTO> findByModule(Long idModule);
	
	QuizDTO convertModelToDTOWithQuestion(Quiz quiz);
}
