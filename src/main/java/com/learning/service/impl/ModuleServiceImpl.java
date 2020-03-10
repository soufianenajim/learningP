package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.ModuleDTO;
import com.learning.model.Module;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService{

	@Override
	public Module save(ModuleDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Module t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<ModuleDTO> findByCriteres(Demande<ModuleDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module convertDTOtoModel(ModuleDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleDTO convertModelToDTO(Module t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<ModuleDTO> convertToListDTO(PartialList<Module> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModuleDTO> convertEntitiesToDtos(List<Module> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Module> convertDtosToEntities(List<ModuleDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
