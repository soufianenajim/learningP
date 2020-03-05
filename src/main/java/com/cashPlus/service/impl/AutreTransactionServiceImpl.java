package com.cashPlus.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.AutreTransactionRepository;
import com.cashPlus.dto.AutreTransactionDTO;
import com.cashPlus.model.AutreTransaction;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.AutreTransactionService;
import com.cashPlus.service.UserService;

@Service
public class AutreTransactionServiceImpl implements AutreTransactionService {
	@Autowired
	AutreTransactionRepository autreTransactionRepository;
	@Autowired
	UserService userService;
	@Override
	public PartialList<AutreTransactionDTO> findByCriteres(Pageable page, String name) {
		Page<AutreTransaction> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = autreTransactionRepository.findAll(page);
		} else {
			resultat = autreTransactionRepository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public AutreTransaction save(AutreTransaction autreTransaction) {

		return autreTransactionRepository.saveAndFlush(autreTransaction);
	}

	@Override
	public AutreTransaction findById(long idAutreTransaction) {

		return autreTransactionRepository.findById(idAutreTransaction).get();
	}

	@Override
	public void delete(AutreTransaction autreTransaction) {
		autreTransactionRepository.deleteById(autreTransaction.getId());
	}

	@Override
	public AutreTransaction convertDTOtoModel(AutreTransactionDTO u) {

		return new AutreTransaction(u.getBorderaux(), u.getDate(), u.getMontantTransfer(),
			u.getRefUser()!=null?userService.convertDTOtoModel(u.getRefUser()):null, u.getInfo());
	}

	@Override
	public PartialList<AutreTransactionDTO> convertToListDTO(PartialList<AutreTransaction> list) {

		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));
	}

	@Override
	public AutreTransactionDTO convertModelToDTO(AutreTransaction u) {

		return new AutreTransactionDTO(u.getId(),u.getCreatedAt(),u.getUpdatedAt(),u.getBorderaux(), u.getDate(), u.getMontantTransfer(),
				u.getRefUser()!=null?u.getRefUser()!=null?userService.convertModelToDTO(u.getRefUser()):null:null, u.getInfo());
	}

}
