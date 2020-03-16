package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.NoteExamDTO;
import com.learning.model.NoteExam;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.NoteExamService;

@Service
public class NoteExamServiceImpl implements NoteExamService {

	@Override
	public NoteExam save(NoteExamDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteExamDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(NoteExam t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<NoteExamDTO> findByCriteres(Demande<NoteExamDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteExam convertDTOtoModel(NoteExamDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteExamDTO convertModelToDTO(NoteExam t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<NoteExamDTO> convertToListDTO(PartialList<NoteExam> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteExamDTO> convertEntitiesToDtos(List<NoteExam> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteExam> convertDtosToEntities(List<NoteExamDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
