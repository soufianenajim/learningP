package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.FactureRepository;
import com.cashPlus.dto.FactureDTO;
import com.cashPlus.model.Facture;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.FactureService;
import com.cashPlus.service.UserService;

@Service
public class FactureServiceImpl implements FactureService {
	@Autowired
	FactureRepository factureRepository;
	@Autowired
	UserService userService;
	@Override
	public PartialList<FactureDTO> findByCriteres(Pageable page, String name) {
		Page<Facture> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = factureRepository.findAll(page);
		} else {
			resultat = factureRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public Facture save(Facture neoSurfExpress) {

		return factureRepository.saveAndFlush(neoSurfExpress);
	}

	@Override
	public Facture findById(long idFacture) {

		return factureRepository.findById(idFacture).get();
	}

	@Override
	public void delete(Facture t) {
		factureRepository.deleteById(t.getId());

	}

	@Override
	public Facture convertDTOtoModel(FactureDTO u) {
	return new Facture(u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null, u.getFrais(), u.getNumTelephone());
	}

	@Override
	public PartialList<FactureDTO> convertToListDTO(PartialList<Facture> list) {
		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public FactureDTO convertModelToDTO(Facture u) {
		
		return new FactureDTO(u.getId(),u.getCreatedAt(),u.getUpdatedAt(), u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertModelToDTO(u.getRefUser()):null, u.getFrais(), u.getNumTelephone());
	}

}
