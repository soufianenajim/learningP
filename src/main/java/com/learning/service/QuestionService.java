package com.learning.service;

import java.util.List;

import com.learning.dto.QuestionDTO;
import com.learning.model.Exam;
import com.learning.model.Exercices;
import com.learning.model.Question;

public interface QuestionService extends CrudService<Question, QuestionDTO> {
	void saveQuestionsByExercices(List<QuestionDTO> questions, Exercices quiz);

	void saveQuestionsByExam(List<QuestionDTO> questions, Exam exam);

	List<QuestionDTO> findByExercices(Long exercicesId);
	
	List<QuestionDTO> findByExam(Long examId);

	void detachExam(Long examId);

	void detachExercices(Long exercicesId);
	
	

}
