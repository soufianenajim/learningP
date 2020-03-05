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

import com.cashPlus.dto.AutreFawatirDTO;
import com.cashPlus.model.AutreFawatir;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.AutreFawatirService;

@RestController
@RequestMapping("/autreFawatir")
public class AutreFawatirResource {
	@Autowired
	AutreFawatirService autreFawatirService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public AutreFawatirDTO findById(@RequestParam Long id) {
		AutreFawatir a = autreFawatirService.findById(id);
		return autreFawatirService.convertModelToDTO(a);
	}

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<AutreFawatirDTO> find(@RequestParam int page, @RequestParam int size,
			@RequestParam String name) {
		return autreFawatirService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@RequestMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE, method = RequestMethod.POST)
	public AutreFawatirDTO save(@RequestBody AutreFawatirDTO autreFawatirDTO) throws IOException {
		AutreFawatir autreFawatir = autreFawatirService.convertDTOtoModel(autreFawatirDTO);
		autreFawatir=autreFawatirService.save(autreFawatir);
			autreFawatirDTO.setCreatedAt(autreFawatir.getCreatedAt());
		autreFawatirDTO.setUpdatedAt(autreFawatir.getUpdatedAt());
		autreFawatirDTO.setId(autreFawatir.getId());
		return autreFawatirDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	@ResponseBody
	public String delete(@RequestParam Long id) {
		AutreFawatir autreFawatir = autreFawatirService.findById(id);
		if (autreFawatir != null && autreFawatir.getId() != null) {
			autreFawatirService.delete(autreFawatir);
		}

		return "deleted success";
	}

}
