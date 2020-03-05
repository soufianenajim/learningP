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

import com.cashPlus.dto.CtmDTO;
import com.cashPlus.model.Ctm;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.CtmService;

@RestController
@RequestMapping("/ctm")
public class CtmResource {
	@Autowired
	CtmService ctmService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public	CtmDTO findById(@RequestParam Long id) {
		Ctm ct=ctmService.findById(id);
		return ctmService.convertModelToDTO(ct);
	}
	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<CtmDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return ctmService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */ 
	@RequestMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE, method = RequestMethod.POST)
	public CtmDTO save(@RequestBody CtmDTO ctmDTO) throws IOException {
		Ctm ctm = ctmService.convertDTOtoModel(ctmDTO);
		ctm=ctmService.save(ctm);
		ctmDTO.setCreatedAt(ctm.getCreatedAt());
		ctmDTO.setUpdatedAt(ctm.getUpdatedAt());
		ctmDTO.setId(ctm.getId());
		return ctmDTO;
		
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		Ctm ctm = ctmService.findById(id);
		if (ctm != null && ctm.getId() != null) {
			ctmService.delete(ctm);
		}

		return "deleted success";
	}
}
