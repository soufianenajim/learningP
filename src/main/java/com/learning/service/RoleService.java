package com.learning.service;

import java.util.List;

import com.learning.dto.RoleDTO;
import com.learning.model.Role;

public interface RoleService extends CrudService<Role, RoleDTO> {
	List<RoleDTO> findAll();
}
