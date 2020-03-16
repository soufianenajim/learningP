package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.ExamDTO;
import com.learning.model.Exam;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	@Override
	public Exam save(ExamDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Exam t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<ExamDTO> findByCriteres(Demande<ExamDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exam convertDTOtoModel(ExamDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamDTO convertModelToDTO(Exam t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<ExamDTO> convertToListDTO(PartialList<Exam> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamDTO> convertEntitiesToDtos(List<Exam> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> convertDtosToEntities(List<ExamDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
