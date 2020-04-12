package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.BranchRepository;
import com.learning.dto.BranchDTO;
import com.learning.model.Branch;
import com.learning.model.Organization;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.BranchService;
import com.learning.service.OrganizationService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private OrganizationService courService;

	// save or update
	@Override
	public BranchDTO save(BranchDTO branchDTO) {
		Branch branch = convertDTOtoModel(branchDTO);
		branch = branchRepository.save(branch);
		return convertModelToDTO(branch);
	}

	@Override
	public BranchDTO findById(long idOut) {
		Optional<Branch> optional = branchRepository.findById(idOut);

		if (optional.isPresent()) {
			Branch branchFromDb = optional.get();
			return convertModelToDTO(branchFromDb);
		}
		return null;
	}

	@Override
	public void delete(Branch branch) {
		branchRepository.delete(branch);
	}

	@Override
	public PartialList<BranchDTO> findByCriteres(Demande<BranchDTO> demande) {

		BranchDTO branch = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Branch> pageBranch = null;
		String name = branch.getName();
		Long idOrganization = branch.getOrganization() != null ? branch.getOrganization().getId() : null;

		pageBranch = idOrganization != null
				? branchRepository.findByNameAndOrganization(name, idOrganization, PageRequest.of(page, size))
				: branchRepository.findByName(name, PageRequest.of(page, size));

		List<BranchDTO> list = convertEntitiesToDtos(pageBranch.getContent());
		Long totalElement = pageBranch.getTotalElements();
		return new PartialList<BranchDTO>(totalElement, list);
	}

	@Override
	public Branch convertDTOtoModel(BranchDTO branchDTO) {
		Branch branch = new Branch();
		branch.setId(branchDTO.getId());
		branch.setName(branchDTO.getName());

		if (branchDTO.getOrganization() != null) {
			branch.setOrganization(courService.convertDTOtoModel(branchDTO.getOrganization()));
		}
		return branch;
	}

	@Override
	public BranchDTO convertModelToDTO(Branch branch) {
		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setId(branch.getId());
		branchDTO.setName(branch.getName());
		Organization cour = branch.getOrganization();
		if (cour != null) {
			branchDTO.setOrganization(courService.convertModelToDTO(branch.getOrganization()));

		}

		branchDTO.setCreatedAt(branch.getCreatedAt());
		branchDTO.setUpdatedAt(branch.getUpdatedAt());
		return branchDTO;
	}

	@Override
	public PartialList<BranchDTO> convertToListDTO(PartialList<Branch> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		branchRepository.deleteById(id);

	}

	@Override
	public List<BranchDTO> convertEntitiesToDtos(List<Branch> branchs) {
		// basic methode
		List<BranchDTO> list = new ArrayList<BranchDTO>();
		for (Branch branch : branchs) {
			list.add(convertModelToDTO(branch));
		}
		return list;
	}

	@Override
	public List<Branch> convertDtosToEntities(List<BranchDTO> branchsDTO) {
		List<Branch> list = new ArrayList<Branch>();
		for (BranchDTO branchDTO : branchsDTO) {
			list.add(convertDTOtoModel(branchDTO));
		}
		return list;
	}

	@Override
	public List<BranchDTO> findAll() {
		List<Branch> list = branchRepository.findAll();
		return convertEntitiesToDtos(list);
	}

	@Override
	public Branch convertDTOtoModelWithOutOrganization(BranchDTO branchDTO) {
		Branch branch = new Branch();
		branch.setId(branchDTO.getId());
		branch.setName(branchDTO.getName());
		return branch;
	}

	@Override
	public BranchDTO convertModelToDTOWithOutOrganization(Branch branch) {
		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setId(branch.getId());
		branchDTO.setName(branch.getName());
		return branchDTO;
	}

	@Override
	public List<BranchDTO> convertEntitiesToDtosWithOutOrganization(List<Branch> list) {
		List<BranchDTO> branchs = new ArrayList<>();
		for (Branch branch : list) {
			branchs.add(convertModelToDTOWithOutOrganization(branch));
		}
		return branchs;
	}

	@Override
	public List<Branch> convertDtosToEntitiesWithOutOrganization(List<BranchDTO> list) {
		List<Branch> branchs = new ArrayList<>();
		for (BranchDTO branch : list) {
			branchs.add(convertDTOtoModelWithOutOrganization(branch));
		}
		return branchs;
	}

	@Override
	public void saveBranchsByOrganization(List<BranchDTO> branchs, Organization organization) {
		for (BranchDTO branchDTO : branchs) {
			Branch branch = convertDTOtoModel(branchDTO);
			branch.setOrganization(organization);
			branchRepository.save(branch);

		}

	}

	@Override
	public void deleteByOrganizationId(Long id) {
	branchRepository.deleteByOrganisation(id);
		
	}


}
