package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.QuizRepository;
import com.learning.dto.QuizDTO;
import com.learning.model.Cour;
import com.learning.model.Quiz;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;
import com.learning.service.QuestionService;
import com.learning.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private CourService courService;
	@Autowired
	private QuestionService questionService;

	@Override
	public QuizDTO save(QuizDTO quizDTO) {
		Quiz quiz = convertDTOtoModel(quizDTO);
		quiz = quizRepository.save(quiz);
		if (quizDTO.getQuestions() != null) {
			questionService.saveQuestionsByQuiz(quizDTO.getQuestions(), quiz);
		}
		return convertModelToDTO(quiz);
	}

	@Override
	public QuizDTO findById(long idOut) {
		Optional<Quiz> optional = quizRepository.findById(idOut);

		if (optional.isPresent()) {
			Quiz quizFromDb = optional.get();
			return convertModelToDTO(quizFromDb);
		}
		return null;
	}

	@Override
	public void delete(Quiz quiz) {
		quizRepository.delete(quiz);
	}

	@Override
	public PartialList<QuizDTO> findByCriteres(Demande<QuizDTO> demande) {

		QuizDTO quiz = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Quiz> pageQuiz = null;
		String name = quiz.getName();
		Long idCour = quiz.getCour() != null ? quiz.getCour().getId() : null;

		pageQuiz = idCour != null ? quizRepository.findByNameAndCour(name, idCour, PageRequest.of(page, size))
				: quizRepository.findByName(name, PageRequest.of(page, size));

		List<QuizDTO> list = convertEntitiesToDtos(pageQuiz.getContent());
		Long totalElement = pageQuiz.getTotalElements();
		return new PartialList<QuizDTO>(totalElement, list);
	}

	@Override
	public Quiz convertDTOtoModel(QuizDTO quizDTO) {
		Quiz quiz = new Quiz();
		quiz.setId(quizDTO.getId());
		quiz.setName(quizDTO.getName());
		if (quizDTO.getCour() != null) {
			quiz.setCour(courService.convertDTOtoModel(quizDTO.getCour()));
		}
		return quiz;
	}

	@Override
	public QuizDTO convertModelToDTO(Quiz quiz) {
		QuizDTO quizDTO = new QuizDTO();
		quizDTO.setId(quiz.getId());
		quizDTO.setName(quiz.getName());
		Cour cour = quiz.getCour();
		if (cour != null) {
			quizDTO.setCour(courService.convertModelToDTO(cour));
		}
		

		quizDTO.setCreatedAt(quiz.getCreatedAt());
		quizDTO.setUpdatedAt(quiz.getUpdatedAt());
		return quizDTO;
	}

	@Override
	public PartialList<QuizDTO> convertToListDTO(PartialList<Quiz> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		quizRepository.deleteById(id);
		questionService.detachQuiz(id);

	}

	@Override
	public List<QuizDTO> convertEntitiesToDtos(List<Quiz> quizs) {
		// basic methode
		List<QuizDTO> list = new ArrayList<QuizDTO>();
		for (Quiz quiz : quizs) {
			list.add(convertModelToDTO(quiz));
		}
		return list;
	}

	@Override
	public List<Quiz> convertDtosToEntities(List<QuizDTO> quizsDTO) {
		List<Quiz> list = new ArrayList<Quiz>();
		for (QuizDTO quizDTO : quizsDTO) {
			list.add(convertDTOtoModel(quizDTO));
		}
		return list;
	}

}
