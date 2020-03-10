package com.learning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.dto.RoleDTO;
import com.learning.model.Role;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Override
	public Role save(RoleDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findById(long idOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Role t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<RoleDTO> findByCriteres(Demande<RoleDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role convertDTOtoModel(RoleDTO u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO convertModelToDTO(Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<RoleDTO> convertToListDTO(PartialList<Role> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleDTO> convertEntitiesToDtos(List<Role> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> convertDtosToEntities(List<RoleDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
