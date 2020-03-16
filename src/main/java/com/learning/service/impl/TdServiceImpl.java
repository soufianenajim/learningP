package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.TdDTO;
import com.learning.model.Td;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.TdService;

@Service
public class TdServiceImpl implements TdService {

	@Override
	public Td save(TdDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TdDTO findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Td t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<TdDTO> findByCriteres(Demande<TdDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Td convertDTOtoModel(TdDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TdDTO convertModelToDTO(Td t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<TdDTO> convertToListDTO(PartialList<Td> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TdDTO> convertEntitiesToDtos(List<Td> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Td> convertDtosToEntities(List<TdDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
