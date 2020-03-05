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

import com.cashPlus.dto.EauElectriciteDTO;
import com.cashPlus.model.EauElectricite;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.EauElectriciteService;

@RestController
@RequestMapping("/eauElectricite")
public class EauElectriciteResource {
	@Autowired
	EauElectriciteService eauElectriciteService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public	EauElectriciteDTO findById(@RequestParam Long id) {
		EauElectricite e=eauElectriciteService.findById(id);
		return eauElectriciteService.convertModelToDTO(e);
	}
	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<EauElectriciteDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return eauElectriciteService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@PostMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public EauElectriciteDTO save(@RequestBody EauElectriciteDTO eauElectriciteDTO) throws IOException {
		EauElectricite eauElectricite = eauElectriciteService.convertDTOtoModel(eauElectriciteDTO);
		eauElectricite=eauElectriciteService.save(eauElectricite);
		eauElectriciteDTO.setCreatedAt(eauElectricite.getCreatedAt());
		eauElectriciteDTO.setUpdatedAt(eauElectricite.getUpdatedAt());
		eauElectriciteDTO.setId(eauElectricite.getId());
		return eauElectriciteDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		EauElectricite eauElectricite = eauElectriciteService.findById(id);
		if (eauElectricite != null && eauElectricite.getId() != null) {
			eauElectriciteService.delete(eauElectricite);
		}

		return "deleted success";
	}

}
