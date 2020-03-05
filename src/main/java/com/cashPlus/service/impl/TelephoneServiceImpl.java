package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.TelephoneRepository;
import com.cashPlus.dto.TelephoneDTO;
import com.cashPlus.model.Telephone;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.TelephoneService;
import com.cashPlus.service.UserService;

@Service
public class TelephoneServiceImpl implements TelephoneService {
	@Autowired
	TelephoneRepository telephoneRepository;

	@Autowired
	UserService userService;

	@Override
	public PartialList<TelephoneDTO> findByCriteres(Pageable page, String name) {
		Page<Telephone> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = telephoneRepository.findAll(page);
		} else {
			resultat = telephoneRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}



	@Override
	public Telephone save(Telephone telephone) {
	
		return telephoneRepository.saveAndFlush(telephone);
	}



	@Override
	public Telephone findById(long idTelephone) {
		
		return telephoneRepository.findById(idTelephone).get();
	}



	@Override
	public void delete(Telephone telephone) {
		telephoneRepository.deleteById(telephone.getId());
	}



	@Override
	public Telephone convertDTOtoModel(TelephoneDTO u) {
	return new Telephone(u.getBorderaux(), u.getDate(), u.getMontantTransfer(), u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null, u.getFrais(), u.getNumTelephone());
	}



	@Override
	public PartialList<TelephoneDTO> convertToListDTO(PartialList<Telephone> list) {
		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}



	@Override
	public TelephoneDTO convertModelToDTO(Telephone t) {
		
		return new TelephoneDTO(t.getId(), t.getCreatedAt(), t.getUpdatedAt(),t.getBorderaux(), t.getDate(), t.getMontantTransfer(), userService.convertModelToDTO(t.getRefUser()), t.getFrais(), t.getNumTelephone());
	}

}
