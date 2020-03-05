package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.NeoSurfPayExpressRepository;
import com.cashPlus.dto.NeoSurfPayExpressDTO;
import com.cashPlus.model.NeoSurfPayExpress;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.NeoSurfPayExpressService;
import com.cashPlus.service.UserService;

@Service
public class NeoSurfPayExpressServiceImpl implements NeoSurfPayExpressService {
	@Autowired
	NeoSurfPayExpressRepository neoSurfPayExpressRepository;
	@Autowired
	UserService userService;
	@Override
	public PartialList<NeoSurfPayExpressDTO> findByCriteres(Pageable page, String name) {
		Page<NeoSurfPayExpress> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = neoSurfPayExpressRepository.findAll(page);
		} else {
			resultat = neoSurfPayExpressRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public NeoSurfPayExpress save(NeoSurfPayExpress neoSurfExpress) {

		return neoSurfPayExpressRepository.saveAndFlush(neoSurfExpress);
	}

	@Override
	public NeoSurfPayExpress findById(long idNeoSurfPayExpress) {

		return neoSurfPayExpressRepository.findById(idNeoSurfPayExpress).get();
	}

	@Override
	public void delete(NeoSurfPayExpress neoSurfExpress) {
		neoSurfPayExpressRepository.deleteById(neoSurfExpress.getId());
	}

	@Override
	public NeoSurfPayExpress convertDTOtoModel(NeoSurfPayExpressDTO u) {
		return new NeoSurfPayExpress(u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null,u.getNumCommande());
	}

	@Override
	public PartialList<NeoSurfPayExpressDTO> convertToListDTO(PartialList<NeoSurfPayExpress> list) {
		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public NeoSurfPayExpressDTO convertModelToDTO(NeoSurfPayExpress u) {
		return new NeoSurfPayExpressDTO(u.getId(),u.getCreatedAt(),u.getUpdatedAt(),u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertModelToDTO(u.getRefUser()):null,u.getNumCommande());
	}

	

}
