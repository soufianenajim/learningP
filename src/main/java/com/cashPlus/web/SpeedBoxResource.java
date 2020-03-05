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

import com.cashPlus.dto.SpeedBoxdDTO;
import com.cashPlus.model.SpeedBox;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.SpeedBoxService;

@RestController
@RequestMapping("/speedBox")
public class SpeedBoxResource {
	@Autowired
	SpeedBoxService speedBoxService;

	
	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public SpeedBox findById(@RequestParam Long id) {

		return speedBoxService.findById(id);
	}
	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<SpeedBoxdDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return speedBoxService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@PostMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public SpeedBoxdDTO save(@RequestBody SpeedBoxdDTO speedBoxDTO) throws IOException {
		SpeedBox speedBox = speedBoxService.convertDTOtoModel(speedBoxDTO);
		speedBox =speedBoxService.save(speedBox);
		speedBoxDTO.setCreatedAt(speedBox.getCreatedAt());
		speedBoxDTO.setUpdatedAt(speedBox.getUpdatedAt());
		speedBoxDTO.setId(speedBox.getId());
		return speedBoxDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		SpeedBox speedBox = speedBoxService.findById(id);
		if (speedBox != null && speedBox.getId() != null) {
			speedBoxService.delete(speedBox);
		}

		return "deleted success";
	}

}
