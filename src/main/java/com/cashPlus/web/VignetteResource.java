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

import com.cashPlus.dto.VignetteDTO;
import com.cashPlus.model.Vignette;
import com.cashPlus.model.base.ConstantBase;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.VignetteService;

@RestController
@RequestMapping("/vignette")
public class VignetteResource {
	@Autowired
	VignetteService vignetteService;

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_ID)
	public VignetteDTO findById(@RequestParam Long id) {
		Vignette v = vignetteService.findById(id);
		return vignetteService.convertModelToDTO(v);
	}

	@ResponseBody
	@GetMapping(ConstantBase.CRUD_REST_FIND_BY_CRITERE)
	public PartialList<VignetteDTO> find(@RequestParam int page, @RequestParam int size, @RequestParam String name) {
		return vignetteService.findByCriteres(PageRequest.of(page, size), name);
	}

	/*
	 * // @ResponseBody // @ResponseStatus(value=HttpStatus.OK)
	 * // @PostMapping(ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	 */
	@PostMapping(value = ConstantBase.CRUD_REST_SAVE_OR_UPDATE)
	public VignetteDTO save(@RequestBody VignetteDTO vignetteDTO) throws IOException {
		Vignette vignette = vignetteService.convertDTOtoModel(vignetteDTO);
		vignette =vignetteService.save(vignette);
		vignetteDTO.setCreatedAt(vignette.getCreatedAt());
		vignetteDTO.setUpdatedAt(vignette.getUpdatedAt());
		vignetteDTO.setId(vignette.getId());
		return vignetteDTO;
	}

	@GetMapping(value = ConstantBase.CRUD_REST_DELETE)
	public String delete(@RequestParam Long id) {
		Vignette vignette = vignetteService.findById(id);
		if (vignette != null && vignette.getId() != null) {
			vignetteService.delete(vignette);
		}

		return "deleted success";
	}

}
