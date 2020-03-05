package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.RoleRepository;
import com.cashPlus.dto.RoleDTO;
import com.cashPlus.model.Role;
import com.cashPlus.model.RoleName;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Override
	public PartialList<RoleDTO> findByCriteres(Pageable page, String name) {
		Page<Role> resultat= roleRepository.findAll(page);

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public Role save(Role role) {

		return roleRepository.saveAndFlush(role);
	}

	@Override
	public Role findById(long idRole) {

		return roleRepository.findById(idRole).get();
	}

	@Override
	public void delete(Role role) {
		roleRepository.deleteById(role.getId());
	}

	@Override
	public Role convertDTOtoModel(RoleDTO u) {

		return new Role(RoleName.valueOf(u.getRoleName()));
	}

	@Override
	public PartialList<RoleDTO> convertToListDTO(PartialList<Role> list) {
		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public RoleDTO convertModelToDTO(Role u) {

		return new RoleDTO(u.getId(), u.getCreatedAt(), u.getUpdatedAt(), u.getName().toString());
	}

}
