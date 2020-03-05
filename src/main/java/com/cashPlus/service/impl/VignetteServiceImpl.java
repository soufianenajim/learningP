package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.VignetteRepository;
import com.cashPlus.dto.VignetteDTO;
import com.cashPlus.model.Vignette;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.UserService;
import com.cashPlus.service.VignetteService;

@Service
public class VignetteServiceImpl implements VignetteService {
	@Autowired
	VignetteRepository vignetteRepository;
	@Autowired
	UserService userService;
	@Override
	public Vignette save(Vignette vignette) {

		return vignetteRepository.saveAndFlush(vignette);
	}

	@Override
	public Vignette findById(long idVignette) {

		return vignetteRepository.findById(idVignette).get();
	}

	@Override
	public void delete(Vignette u) {
		vignetteRepository.deleteById(u.getId());

	}

	@Override
	public PartialList<VignetteDTO> findByCriteres(Pageable page, String name) {
		Page<Vignette> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = vignetteRepository.findAll(page);
		} else {
			resultat = vignetteRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public Vignette convertDTOtoModel(VignetteDTO u) {
		return new Vignette(u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null, u.getFrais(), u.getMatriculeVehicule());
	}

	@Override
	public PartialList<VignetteDTO> convertToListDTO(PartialList<Vignette> list) {
		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public VignetteDTO convertModelToDTO(Vignette u) {
		return new VignetteDTO(u.getId(),u.getCreatedAt(),u.getUpdatedAt(),u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertModelToDTO(u.getRefUser()):null, u.getFrais(), u.getMatriculeVehicule());	
	}
}
