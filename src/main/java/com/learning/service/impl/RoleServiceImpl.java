package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.RoleRepository;
import com.learning.dto.RoleDTO;
import com.learning.model.Role;
import com.learning.model.RoleName;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		Role role = convertDTOtoModel(roleDTO);
		role = roleRepository.save(role);
		return convertModelToDTO(role);
	}

	@Override
	public RoleDTO findById(long idOut) {
		Optional<Role> optional = roleRepository.findById(idOut);

		if (optional.isPresent()) {
			Role roleFromDb = optional.get();
			return convertModelToDTO(roleFromDb);
		}
		return null;
	}

	@Override
	public void delete(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public PartialList<RoleDTO> findByCriteres(Demande<RoleDTO> demande) {

		return null;
	}

	@Override
	public Role convertDTOtoModel(RoleDTO roleDTO) {
		Role role = new Role();
		role.setId(roleDTO.getId());
		role.setName(RoleName.valueOf(roleDTO.getName()));

		return role;
	}

	@Override
	public RoleDTO convertModelToDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName().toString());

		roleDTO.setCreatedAt(role.getCreatedAt());
		roleDTO.setUpdatedAt(role.getUpdatedAt());
		return roleDTO;
	}

	@Override
	public PartialList<RoleDTO> convertToListDTO(PartialList<Role> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);

	}

	@Override
	public List<RoleDTO> convertEntitiesToDtos(List<Role> roles) {
		// basic methode
		List<RoleDTO> list = new ArrayList<RoleDTO>();
		for (Role role : roles) {
			list.add(convertModelToDTO(role));
		}
		return list;
	}

	@Override
	public List<Role> convertDtosToEntities(List<RoleDTO> rolesDTO) {
		List<Role> list = new ArrayList<Role>();
		for (RoleDTO roleDTO : rolesDTO) {
			list.add(convertDTOtoModel(roleDTO));
		}
		return list;
	}

	@Override
	public List<RoleDTO> findAll() {
		return convertEntitiesToDtos(roleRepository.findAll());
	}

}
