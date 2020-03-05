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

import com.cashPlus.dto.OutDTO;
import com.cashPlus.model.Out;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.OutService;

@RestController
@RequestMapping("/out")
public class OutResource {
	@Autowired
	OutService outService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public OutDTO findById(@RequestParam Long id) {
		Out o = outService.findById(id);
		return outService.convertModelToDTO(o);
	}

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<OutDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return outService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@RequestMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE, method = RequestMethod.POST)
	public OutDTO save(@RequestBody OutDTO outDTO) throws IOException {
		Out out = outService.convertDTOtoModel(outDTO);
		out =outService.save(out);
		outDTO.setCreatedAt(out.getCreatedAt());
		outDTO.setUpdatedAt(out.getUpdatedAt());
		outDTO.setId(out.getId());
		return outDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		Out out = outService.findById(id);
		if (out != null && out.getId() != null) {
			outService.delete(out);
		}

		return "deleted success";
	}
}
