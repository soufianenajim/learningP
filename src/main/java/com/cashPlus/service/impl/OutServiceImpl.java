package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.OutRepository;
import com.cashPlus.dto.OutDTO;
import com.cashPlus.model.Out;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.OutService;
import com.cashPlus.service.UserService;

@Service
public class OutServiceImpl implements OutService {
	@Autowired
	OutRepository outRepository;
	@Autowired
	UserService userService;
	@Override
	public PartialList<OutDTO> findByCriteres(Pageable page, String name) {
		Page<Out> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = outRepository.findAll(page);
		} else {
			resultat = outRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public Out save(Out neoSurfExpress) {

		return outRepository.saveAndFlush(neoSurfExpress);
	}

	@Override
	public Out findById(long idOut) {

		return outRepository.findById(idOut).get();
	}

	@Override
	public void delete(Out neoSurfExpress) {
		outRepository.deleteById(neoSurfExpress.getId());
	}

	@Override
	public Out convertDTOtoModel(OutDTO u) {
		
	return	new Out(u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null, u.getFrais());
	}

	@Override
	public PartialList<OutDTO> convertToListDTO(PartialList<Out> list) {
		
		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public OutDTO convertModelToDTO(Out u) {
		return	new OutDTO(u.getId(),u.getCreatedAt(),u.getUpdatedAt(),u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertModelToDTO(u.getRefUser()):null, u.getFrais());
	}

	

}
