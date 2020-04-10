package com.learning.service;

import java.util.List;

import com.learning.dto.QuestionDTO;
import com.learning.model.Exam;
import com.learning.model.Question;
import com.learning.model.Quiz;
import com.learning.model.Td;

public interface QuestionService extends CrudService<Question, QuestionDTO> {
	void saveQuestionsByQuiz(List<QuestionDTO> questions, Quiz quiz);

	void saveQuestionsByExam(List<QuestionDTO> questions, Exam exam);
	
	void saveQuestionsByTd(List<QuestionDTO> questions, Td td);

	List<QuestionDTO> findByQuiz(Long quizId);
	
	List<QuestionDTO> findByTd(Long tdId);
	
	List<QuestionDTO> findByExam(Long examId);

	void detachExam(Long examId);

	void detachQuiz(Long quizId);
	
	void detachTd(Long tdId);

}
