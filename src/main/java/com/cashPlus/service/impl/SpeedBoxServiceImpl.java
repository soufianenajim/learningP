package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.SpeedBoxRepository;
import com.cashPlus.dto.SpeedBoxdDTO;
import com.cashPlus.model.SpeedBox;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.SpeedBoxService;
import com.cashPlus.service.UserService;

@Service
public class SpeedBoxServiceImpl implements SpeedBoxService {
	@Autowired
	SpeedBoxRepository speedBoxRepository;
	@Autowired
	UserService userService;

	@Override
	public PartialList<SpeedBoxdDTO> findByCriteres(Pageable page, String name) {
		Page<SpeedBox> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = speedBoxRepository.findAll(page);
		} else {
			resultat = speedBoxRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public SpeedBox save(SpeedBox speedBox) {

		return speedBoxRepository.saveAndFlush(speedBox);
	}

	@Override
	public SpeedBox findById(long idSpeedBox) {

		return speedBoxRepository.findById(idSpeedBox).get();
	}

	@Override
	public void delete(SpeedBox speedBox) {
		speedBoxRepository.deleteById(speedBox.getId());
	}

	@Override
	public SpeedBox convertDTOtoModel(SpeedBoxdDTO u) {

		return new SpeedBox(u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null,
				u.getNumColis());
	}

	@Override
	public PartialList<SpeedBoxdDTO> convertToListDTO(PartialList<SpeedBox> list) {
		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public SpeedBoxdDTO convertModelToDTO(SpeedBox u) {
		return new SpeedBoxdDTO(u.getId(), u.getCreatedAt(), u.getUpdatedAt(), u.getBorderaux(), u.getDate(),
				u.getMontantTransfer(), u.getRefUser()!=null?userService.convertModelToDTO(u.getRefUser()):null, u.getNumColis());
	}

}
