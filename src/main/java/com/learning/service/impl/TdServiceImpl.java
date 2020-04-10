package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.TdRepository;
import com.learning.dto.TdDTO;
import com.learning.model.Cour;
import com.learning.model.Td;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CourService;
import com.learning.service.QuestionService;
import com.learning.service.TdService;

@Service
public class TdServiceImpl implements TdService {

	@Autowired
	private TdRepository tdRepository;
	@Autowired
	private CourService courService;
	
	@Autowired
	private QuestionService questionService;

	@Override
	public TdDTO save(TdDTO tdDTO) {
		Td td = convertDTOtoModel(tdDTO);
		td = tdRepository.save(td);
		if (tdDTO.getQuestions() != null) {
			questionService.saveQuestionsByTd(tdDTO.getQuestions(), td);
		}
		return convertModelToDTO(td);
	}

	@Override
	public TdDTO findById(long idOut) {
		Optional<Td> optional = tdRepository.findById(idOut);

		if (optional.isPresent()) {
			Td tdFromDb = optional.get();
			return convertModelToDTO(tdFromDb);
		}
		return null;
	}

	@Override
	public void delete(Td td) {
		tdRepository.delete(td);
	}

	@Override
	public PartialList<TdDTO> findByCriteres(Demande<TdDTO> demande) {

		TdDTO td = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Td> pageTd = null;
		String name = td.getName();
		Long idCour = td.getCour() != null ? td.getCour().getId() : null;

		pageTd = idCour != null ? tdRepository.findByNameAndCour(name, idCour, PageRequest.of(page, size))
				: tdRepository.findByName(name, PageRequest.of(page, size));

		List<TdDTO> list = convertEntitiesToDtos(pageTd.getContent());
		Long totalElement = pageTd.getTotalElements();

		return new PartialList<TdDTO>(totalElement, list);
	}

	@Override
	public Td convertDTOtoModel(TdDTO tdDTO) {
		Td td = new Td();
		td.setId(tdDTO.getId());
		td.setName(tdDTO.getName());
		if (tdDTO.getCour() != null) {
			td.setCour(courService.convertDTOtoModel(tdDTO.getCour()));
		}
		return td;
	}

	@Override
	public TdDTO convertModelToDTO(Td td) {
		TdDTO tdDTO = new TdDTO();
		tdDTO.setId(td.getId());
		tdDTO.setName(td.getName());

		Cour cour = td.getCour();

		if (cour != null) {
			tdDTO.setCour(courService.convertModelToDTO(cour));
		}

		tdDTO.setCreatedAt(td.getCreatedAt());
		tdDTO.setUpdatedAt(td.getUpdatedAt());
		return tdDTO;
	}

	@Override
	public PartialList<TdDTO> convertToListDTO(PartialList<Td> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		Boolean existed = tdRepository.existsById(id);
		if (existed) {
			tdRepository.deleteById(id);
		}
	}

	@Override
	public List<TdDTO> convertEntitiesToDtos(List<Td> tds) {
		// basic methode
		List<TdDTO> list = new ArrayList<TdDTO>();
		for (Td td : tds) {
			list.add(convertModelToDTO(td));
		}
		return list;
	}

	@Override
	public List<Td> convertDtosToEntities(List<TdDTO> tdsDTO) {
		List<Td> list = new ArrayList<Td>();
		for (TdDTO tdDTO : tdsDTO) {
			list.add(convertDTOtoModel(tdDTO));
		}
		return list;
	}

}
