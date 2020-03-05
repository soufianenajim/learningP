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

import com.cashPlus.dto.AutreTransactionDTO;
import com.cashPlus.model.AutreTransaction;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.AutreTransactionService;

@RestController
@RequestMapping("/autreTransaction")
public class AutreTransactionResource {
	@Autowired
	AutreTransactionService autreTransactionService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public	AutreTransactionDTO findById(@RequestParam Long id) {
		AutreTransaction a=autreTransactionService.findById(id);
		return autreTransactionService.convertModelToDTO(a);
	}
	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<AutreTransactionDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return autreTransactionService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */ 
	@RequestMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE, method = RequestMethod.POST)
	public AutreTransactionDTO save(@RequestBody AutreTransactionDTO autreTransactionDTO) throws IOException {
		AutreTransaction autreTransaction = autreTransactionService.convertDTOtoModel(autreTransactionDTO);
		autreTransaction =autreTransactionService.save(autreTransaction);
		autreTransactionDTO.setCreatedAt(autreTransaction.getCreatedAt());
		autreTransactionDTO.setUpdatedAt(autreTransaction.getUpdatedAt());
		autreTransactionDTO.setId(autreTransaction.getId());
		return autreTransactionDTO;
	}
@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		AutreTransaction autreTransaction = autreTransactionService.findById(id);
		if (autreTransaction != null && autreTransaction.getId() != null) {
			autreTransactionService.delete(autreTransaction);
		}

		return "deleted success";
	}
}
