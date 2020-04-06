package com.learning.service;

import java.util.List;

import com.learning.dto.QuestionDTO;
import com.learning.model.Exam;
import com.learning.model.Question;
import com.learning.model.Quiz;

public interface QuestionService extends CrudService<Question, QuestionDTO> {
	void saveQuestionsByQuiz(List<QuestionDTO> questions, Quiz quiz);

	void saveQuestionsByExam(List<QuestionDTO> questions, Exam exam);

	List<QuestionDTO> findByQuiz(Long quizId);

	List<QuestionDTO> findByExam(Long examId);

	void detachExam(Long examId);

	void detachQuiz(Long quizId);

}
