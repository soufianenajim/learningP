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

import com.cashPlus.dto.InDTO;
import com.cashPlus.model.In;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.InService;

@RestController
@RequestMapping("/in")
public class InResource {
	@Autowired
	InService inService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public InDTO findById(@RequestParam Long id) {
		In i = inService.findById(id);
		return inService.convertModelToDTO(i);
	}

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<InDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return inService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@RequestMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE, method = RequestMethod.POST)
	public InDTO save(@RequestBody InDTO inDTO) throws IOException {
		In in = inService.convertDTOtoModel(inDTO);
		in =inService.save(in);
		inDTO.setCreatedAt(in.getCreatedAt());
		inDTO.setUpdatedAt(in.getUpdatedAt());
		inDTO.setId(in.getId());
		return inDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		In in = inService.findById(id);
		if (in != null && in.getId() != null) {
			inService.delete(in);
		}

		return "deleted success";
	}
}
