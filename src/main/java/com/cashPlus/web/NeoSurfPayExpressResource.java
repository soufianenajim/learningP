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

import com.cashPlus.dto.NeoSurfPayExpressDTO;
import com.cashPlus.model.NeoSurfPayExpress;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.NeoSurfPayExpressService;

@RestController
@RequestMapping("/neoSurfPayExpress")
public class NeoSurfPayExpressResource {
	@Autowired
	NeoSurfPayExpressService neoSurfPayExpressService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public NeoSurfPayExpressDTO findById(@RequestParam Long id) {
		NeoSurfPayExpress n = neoSurfPayExpressService.findById(id);
		return neoSurfPayExpressService.convertModelToDTO(n);
	}

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<NeoSurfPayExpressDTO> find(@RequestParam int page, @RequestParam int size,
			@RequestParam String name) {
		return neoSurfPayExpressService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@RequestMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE, method = RequestMethod.POST)
	public NeoSurfPayExpressDTO save(@RequestBody NeoSurfPayExpressDTO neoSurfPayExpressDTO)
			throws IOException {
		NeoSurfPayExpress neoSurfPayExpress = neoSurfPayExpressService.convertDTOtoModel(neoSurfPayExpressDTO);
		neoSurfPayExpress =neoSurfPayExpressService.save(neoSurfPayExpress);
		neoSurfPayExpressDTO.setCreatedAt(neoSurfPayExpress.getCreatedAt());
		neoSurfPayExpressDTO.setUpdatedAt(neoSurfPayExpress.getUpdatedAt());
		neoSurfPayExpressDTO.setId(neoSurfPayExpress.getId());
		return neoSurfPayExpressDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		NeoSurfPayExpress neoSurfPayExpress = neoSurfPayExpressService.findById(id);
		if (neoSurfPayExpress != null && neoSurfPayExpress.getId() != null) {
			neoSurfPayExpressService.delete(neoSurfPayExpress);
		}

		return "deleted success";
	}
}
