package com.cashPlus.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cashPlus.dto.RoleDTO;
import com.cashPlus.model.Role;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleResource {
	@Autowired
	RoleService roleService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public Role findById(@RequestParam Long id) {

		return roleService.findById(id);
	}
	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<RoleDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return roleService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@PostMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public RoleDTO save(@RequestBody RoleDTO roleDTO) throws IOException {
		Role role = roleService.convertDTOtoModel(roleDTO);
		role =roleService.save(role);
		roleDTO.setCreatedAt(role.getCreatedAt());
		roleDTO.setUpdatedAt(role.getUpdatedAt());
		roleDTO.setId(role.getId());
		return roleDTO;
	}

	@DeleteMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		Role role = roleService.findById(id);
		if (role != null && role.getId() != null) {
			roleService.delete(role);
		}

		return "deleted success";
	}

}
