package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.QuestionRepository;
import com.learning.dto.QuestionDTO;
import com.learning.model.Exam;
import com.learning.model.Question;
import com.learning.model.Quiz;
import com.learning.model.Td;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ExamService;
import com.learning.service.QuestionService;
import com.learning.service.QuizService;
import com.learning.service.TdService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private TdService tdService;

	@Autowired
	private QuizService quizService;

	@Autowired
	private ExamService examService;

	@Override
	public QuestionDTO save(QuestionDTO questionDTO) {
		Question question = convertDTOtoModel(questionDTO);
		question = questionRepository.save(question);
		return convertModelToDTO(question);
	}

	@Override
	public QuestionDTO findById(long idOut) {
		Optional<Question> optional = questionRepository.findById(idOut);

		if (optional.isPresent()) {
			Question questionFromDb = optional.get();
			return convertModelToDTO(questionFromDb);
		}
		return null;
	}

	@Override
	public void delete(Question question) {
		questionRepository.delete(question);
	}

	@Override
	public PartialList<QuestionDTO> findByCriteres(Demande<QuestionDTO> demande) {

		QuestionDTO question = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Question> pageQuestion = null;
		if (question.getTd() != null) {
			pageQuestion = questionRepository.findByCodeAndTd(question.getCode(), question.getTd().getId(),
					PageRequest.of(page, size));
		} else if (question.getQuiz() != null) {
			pageQuestion = questionRepository.findByCodeAndQuiz(question.getCode(), question.getQuiz().getId(),
					PageRequest.of(page, size));
		} else {
			pageQuestion = questionRepository.findByCodeAndExam(question.getCode(), question.getExam().getId(),
					PageRequest.of(page, size));
		}

		List<QuestionDTO> list = convertEntitiesToDtos(pageQuestion.getContent());
		int totalElement = pageQuestion.getNumberOfElements();

		return new PartialList<QuestionDTO>(totalElement, list);
	}

	@Override
	public Question convertDTOtoModel(QuestionDTO questionDTO) {
		Question question = new Question();
		question.setId(questionDTO.getId());
		question.setName(questionDTO.getName());
		question.setCode(questionDTO.getCode());
		question.setCorrectComment(questionDTO.getCorrectComment());
		if (questionDTO.getTd() != null) {
			question.setTd(tdService.convertDTOtoModel(questionDTO.getTd()));
		}
		if (questionDTO.getQuiz() != null) {
			question.setQuiz(quizService.convertDTOtoModel(questionDTO.getQuiz()));
		}
		if (questionDTO.getExam() != null) {
			question.setExam(examService.convertDTOtoModel(questionDTO.getExam()));
		}
		return question;
	}

	@Override
	public QuestionDTO convertModelToDTO(Question question) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setId(question.getId());
		questionDTO.setName(question.getName());
		questionDTO.setCode(question.getCode());
		questionDTO.setCorrectComment(question.getCorrectComment());
		Td td = question.getTd();
		Exam exam = question.getExam();
		Quiz quiz = question.getQuiz();
		if (td != null) {
			questionDTO.setTd(tdService.convertModelToDTO(td));
		}
		if (quiz != null) {
			questionDTO.setQuiz(quizService.convertModelToDTO(quiz));
		}
		if (exam != null) {
			questionDTO.setExam(examService.convertModelToDTO(exam));
		}
		questionDTO.setCreatedAt(question.getCreatedAt());
		questionDTO.setUpdatedAt(question.getUpdatedAt());
		return questionDTO;
	}

	@Override
	public PartialList<QuestionDTO> convertToListDTO(PartialList<Question> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		questionRepository.deleteById(id);

	}

	@Override
	public List<QuestionDTO> convertEntitiesToDtos(List<Question> questions) {
		// basic methode
		List<QuestionDTO> list = new ArrayList<QuestionDTO>();
		for (Question question : questions) {
			list.add(convertModelToDTO(question));
		}
		return list;
	}

	@Override
	public List<Question> convertDtosToEntities(List<QuestionDTO> questionsDTO) {
		List<Question> list = new ArrayList<Question>();
		for (QuestionDTO questionDTO : questionsDTO) {
			list.add(convertDTOtoModel(questionDTO));
		}
		return list;
	}

}
