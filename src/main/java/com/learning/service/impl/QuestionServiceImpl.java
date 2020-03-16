package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.QuestionDTO;
import com.learning.model.Question;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Override
	public Question save(QuestionDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Question t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<QuestionDTO> findByCriteres(Demande<QuestionDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question convertDTOtoModel(QuestionDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionDTO convertModelToDTO(Question t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<QuestionDTO> convertToListDTO(PartialList<Question> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionDTO> convertEntitiesToDtos(List<Question> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> convertDtosToEntities(List<QuestionDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
