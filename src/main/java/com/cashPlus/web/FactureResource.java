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

import com.cashPlus.dto.FactureDTO;
import com.cashPlus.model.Facture;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.FactureService;

@RestController
@RequestMapping("/facture")
public class FactureResource {
	@Autowired
	FactureService factureService;
	
	

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public	FactureDTO findById(@RequestParam Long id) {
		Facture f=factureService.findById(id);
		return factureService.convertModelToDTO(f);
	}
	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<FactureDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return factureService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@PostMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public FactureDTO save(@RequestBody FactureDTO factureDTO) throws IOException {
		Facture facture = factureService.convertDTOtoModel(factureDTO);
		facture =factureService.save(facture);
		factureDTO.setCreatedAt(facture.getCreatedAt());
		factureDTO.setUpdatedAt(facture.getUpdatedAt());
		factureDTO.setId(facture.getId());
		return factureDTO;
		
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		Facture facture = factureService.findById(id);
		if (facture != null && facture.getId() != null) {
			factureService.delete(facture);
		}

		return "deleted success";
	}

}
