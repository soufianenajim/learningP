package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.CourDTO;
import com.learning.model.Cour;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;

@Service
public class CourServiceImpl implements CourService {

	@Override
	public Cour save(CourDTO courDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cour t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<CourDTO> findByCriteres(Demande<CourDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cour convertDTOtoModel(CourDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourDTO convertModelToDTO(Cour t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<CourDTO> convertToListDTO(PartialList<Cour> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourDTO> convertEntitiesToDtos(List<Cour> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cour> convertDtosToEntities(List<CourDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
