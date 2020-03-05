package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.DeviseRepository;
import com.cashPlus.dto.DeviseDTO;
import com.cashPlus.model.Devise;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.DeviseService;
import com.cashPlus.service.UserService;

@Service
public class DeviseServiceImpl implements DeviseService {
	@Autowired
	DeviseRepository deviseRepository;
	@Autowired
	UserService userService;

	@Override
	public PartialList<DeviseDTO> findByCriteres(Pageable page, String name) {
		Page<Devise> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = deviseRepository.findAll(page);
		} else {
			resultat = deviseRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public Devise save(Devise devise) {

		return deviseRepository.saveAndFlush(devise);
	}

	@Override
	public Devise findById(long idDevise) {

		return deviseRepository.findById(idDevise).get();
	}

	@Override
	public void delete(Devise devise) {
		deviseRepository.deleteById(devise.getId());
	}

	@Override
	public Devise convertDTOtoModel(DeviseDTO u) {

		return new Devise(u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null,
				u.getQualiteClient());
	}

	@Override
	public PartialList<DeviseDTO> convertToListDTO(PartialList<Devise> list) {

		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public DeviseDTO convertModelToDTO(Devise u) {

		return new DeviseDTO(u.getId(), u.getCreatedAt(), u.getUpdatedAt(), u.getBorderaux(), u.getDate(),
				u.getMontantTransfer(), u.getRefUser()!=null?u.getRefUser()!=null?userService.convertModelToDTO(u.getRefUser()):null:null, u.getQualiteClient());
	}

}
