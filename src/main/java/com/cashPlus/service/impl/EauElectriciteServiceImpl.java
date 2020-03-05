package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.EauElectriciteRepository;
import com.cashPlus.dto.EauElectriciteDTO;
import com.cashPlus.model.EauElectricite;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.EauElectriciteService;
import com.cashPlus.service.UserService;

@Service
public class EauElectriciteServiceImpl implements EauElectriciteService {
	@Autowired
	EauElectriciteRepository eauElectriciteRepository;
	@Autowired
	UserService userService;

	@Override
	public PartialList<EauElectriciteDTO> findByCriteres(Pageable page, String name) {
		Page<EauElectricite> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = eauElectriciteRepository.findAll(page);
		} else {
			resultat = eauElectriciteRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public EauElectricite save(EauElectricite eauElectricite) {

		return eauElectriciteRepository.saveAndFlush(eauElectricite);
	}

	@Override
	public EauElectricite findById(long idEauElectricite) {

		return eauElectriciteRepository.findById(idEauElectricite).get();
	}

	@Override
	public void delete(EauElectricite eauElectricite) {
		eauElectriciteRepository.deleteById(eauElectricite.getId());
	}

	@Override
	public EauElectricite convertDTOtoModel(EauElectriciteDTO u) {
		return new EauElectricite(u.getBorderaux(), u.getDate(), u.getMontantTransfer(),
				u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null, u.getFrais(), u.getNumFacture());
	}

	@Override
	public PartialList<EauElectriciteDTO> convertToListDTO(PartialList<EauElectricite> list) {

		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public EauElectriciteDTO convertModelToDTO(EauElectricite u) {
		return new EauElectriciteDTO(u.getId(), u.getCreatedAt(), u.getUpdatedAt(), u.getBorderaux(), u.getDate(),
				u.getMontantTransfer(), u.getRefUser()!=null?userService.convertModelToDTO(u.getRefUser()):null, u.getFrais(), u.getNumFacture());
	}

}
