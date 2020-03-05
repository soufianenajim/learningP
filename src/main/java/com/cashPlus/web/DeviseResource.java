package com.cashPlus.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cashPlus.dto.DeviseDTO;
import com.cashPlus.model.Devise;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.DeviseService;

@RestController
@RequestMapping("/devise")
public class DeviseResource {
	@Autowired
	DeviseService deviseService;


	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public	DeviseDTO findById(@RequestParam Long id) {
		Devise d=deviseService.findById(id);
		return deviseService.convertModelToDTO(d);
	}
	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<DeviseDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return deviseService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */ 
	@RequestMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE, method = RequestMethod.POST)
	public DeviseDTO save(@RequestBody DeviseDTO deviseDTO) throws IOException {
		Devise devise = deviseService.convertDTOtoModel(deviseDTO);
		devise =deviseService.save(devise);
		deviseDTO.setCreatedAt(devise.getCreatedAt());
		deviseDTO.setUpdatedAt(devise.getUpdatedAt());
		deviseDTO.setId(devise.getId());
		return deviseDTO;
	}
	
	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		Devise devise = deviseService.findById(id);
		if (devise != null && devise.getId() != null) {
			deviseService.delete(devise);
		}

		return "deleted success";
	}
}
