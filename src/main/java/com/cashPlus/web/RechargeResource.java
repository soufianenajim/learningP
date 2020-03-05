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

import com.cashPlus.dto.RechargeDTO;
import com.cashPlus.model.Recharge;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.RechargeService;

@RestController
@RequestMapping("/recharge")
public class RechargeResource {
	@Autowired
	RechargeService rechargeService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public RechargeDTO findById(@RequestParam Long id) {
		Recharge r = rechargeService.findById(id);
		return rechargeService.convertModelToDTO(r);
	}

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<RechargeDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return rechargeService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@PostMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public RechargeDTO save(@RequestBody RechargeDTO rechargeDTO) throws IOException {
		Recharge recharge = rechargeService.convertDTOtoModel(rechargeDTO);
		recharge =rechargeService.save(recharge);
		rechargeDTO.setCreatedAt(recharge.getCreatedAt());
		rechargeDTO.setUpdatedAt(recharge.getUpdatedAt());
		rechargeDTO.setId(recharge.getId());
		return rechargeDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		Recharge recharge = rechargeService.findById(id);
		if (recharge != null && recharge.getId() != null) {
			rechargeService.delete(recharge);
		}

		return "deleted success";
	}

}
