package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.OrganizationRepository;
import com.learning.dto.OrganizationDTO;
import com.learning.model.Branch;
import com.learning.model.Level;
import com.learning.model.Organization;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.BranchService;
import com.learning.service.LevelService;
import com.learning.service.OrganizationService;

@Service
public class OranizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private BranchService branchService;
	@Autowired
	private LevelService levelService;

	// save or update
	@Override
	public OrganizationDTO save(OrganizationDTO organizationDTO) {
		Organization organization = convertDTOtoModel(organizationDTO);
		organization = organizationRepository.save(organization);
		if (organizationDTO.getBranchs() != null) {
			branchService.saveBranchsByOrganization(organizationDTO.getBranchs(), organization);
		}
		if (organizationDTO.getLevels() != null) {
			levelService.saveLevelsByOrganization(organizationDTO.getLevels(), organization);
		}
		return convertModelToDTO(organization);
	}

	@Override
	public OrganizationDTO findById(long idOut) {
		Optional<Organization> optional = organizationRepository.findById(idOut);

		if (optional.isPresent()) {
			Organization organizationFromDb = optional.get();
			return convertModelToDTO(organizationFromDb);
		}
		return null;
	}

	@Override
	public void delete(Organization organization) {
		organizationRepository.delete(organization);
	}

	@Override
	public PartialList<OrganizationDTO> findByCriteres(Demande<OrganizationDTO> demande) {

		OrganizationDTO organization = demande.getModel();
		int page = demande.getPage();
		int size = demande.getSize();
		Page<Organization> pageOrganization = null;
		String name = organization.getName();

		pageOrganization = organizationRepository.findByName(name, PageRequest.of(page, size));

		List<OrganizationDTO> list = convertEntitiesToDtos(pageOrganization.getContent());
		Long totalElement = pageOrganization.getTotalElements();
		return new PartialList<OrganizationDTO>(totalElement, list);
	}

	@Override
	public Organization convertDTOtoModel(OrganizationDTO organizationDTO) {
		Organization organization = new Organization();
		organization.setId(organizationDTO.getId());
		organization.setName(organizationDTO.getName());

		return organization;
	}

	@Override
	public OrganizationDTO convertModelToDTO(Organization organization) {
		OrganizationDTO organizationDTO = new OrganizationDTO();
		organizationDTO.setId(organization.getId());
		organizationDTO.setName(organization.getName());
		List<Branch> branchs = organization.getBranchs();
		List<Level> levels = organization.getLevels();
		if (branchs != null) {
			organizationDTO.setBranchs(branchService.convertEntitiesToDtosWithOutOrganization(branchs));
		}
		if (levels != null) {
			organizationDTO.setLevels(levelService.convertEntitiesToDtosWithOutOrganization(levels));
		}
		organizationDTO.setCreatedAt(organization.getCreatedAt());
		organizationDTO.setUpdatedAt(organization.getUpdatedAt());
		return organizationDTO;
	}

	@Override
	public PartialList<OrganizationDTO> convertToListDTO(PartialList<Organization> list) {

		return null;
	}

	@Override
	public void deleteById(Long id) {
		organizationRepository.deleteById(id);
		branchService.deleteByOrganizationId(id);
		levelService.deleteByOrganizationId(id);
	}

	@Override
	public List<OrganizationDTO> convertEntitiesToDtos(List<Organization> organizations) {
		// basic methode
		List<OrganizationDTO> list = new ArrayList<OrganizationDTO>();
		for (Organization organization : organizations) {
			list.add(convertModelToDTO(organization));
		}
		return list;
	}

	@Override
	public List<Organization> convertDtosToEntities(List<OrganizationDTO> organizationsDTO) {
		List<Organization> list = new ArrayList<Organization>();
		for (OrganizationDTO organizationDTO : organizationsDTO) {
			list.add(convertDTOtoModel(organizationDTO));
		}
		return list;
	}

	@Override
	public List<OrganizationDTO> findAll() {
		List<Organization> list = organizationRepository.findAll();
		return convertEntitiesToDtos(list);
	}

}
