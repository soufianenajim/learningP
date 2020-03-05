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

import com.cashPlus.dto.TelephoneDTO;
import com.cashPlus.model.Telephone;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.TelephoneService;

@RestController
@RequestMapping("/telephone")
public class TelephoneResource {
	@Autowired
	TelephoneService telephoneService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public TelephoneDTO findById(@RequestParam Long id) {
		Telephone t = telephoneService.findById(id);

		return telephoneService.convertModelToDTO(t);
	}

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<TelephoneDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return telephoneService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@PostMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public TelephoneDTO save(@RequestBody TelephoneDTO telephoneDTO) throws IOException {
		Telephone telephone = telephoneService.convertDTOtoModel(telephoneDTO);
		telephone =telephoneService.save(telephone);
		telephoneDTO.setCreatedAt(telephone.getCreatedAt());
		telephoneDTO.setUpdatedAt(telephone.getUpdatedAt());
		telephoneDTO.setId(telephone.getId());
		return telephoneDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		Telephone telephone = telephoneService.findById(id);
		if (telephone != null && telephone.getId() != null) {
			telephoneService.delete(telephone);
		}

		return "deleted success";
	}

}
