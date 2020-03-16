package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.NoteQuizDTO;
import com.learning.model.NoteQuiz;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.NoteQuizService;

@Service
public class NoteQuizServiceImpl implements NoteQuizService {

	@Override
	public NoteQuizDTO save(NoteQuizDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteQuizDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(NoteQuiz t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<NoteQuizDTO> findByCriteres(Demande<NoteQuizDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteQuiz convertDTOtoModel(NoteQuizDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteQuizDTO convertModelToDTO(NoteQuiz t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<NoteQuizDTO> convertToListDTO(PartialList<NoteQuiz> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteQuizDTO> convertEntitiesToDtos(List<NoteQuiz> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteQuiz> convertDtosToEntities(List<NoteQuizDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
