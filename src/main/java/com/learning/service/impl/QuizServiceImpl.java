package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.QuizDTO;
import com.learning.model.Quiz;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Override
	public QuizDTO save(QuizDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuizDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Quiz t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<QuizDTO> findByCriteres(Demande<QuizDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz convertDTOtoModel(QuizDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuizDTO convertModelToDTO(Quiz t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<QuizDTO> convertToListDTO(PartialList<Quiz> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuizDTO> convertEntitiesToDtos(List<Quiz> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> convertDtosToEntities(List<QuizDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
