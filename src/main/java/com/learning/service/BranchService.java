package com.learning.service;

import java.util.List;

import com.learning.dto.BranchDTO;
import com.learning.model.Branch;
import com.learning.model.Organization;

public interface BranchService extends CrudService<Branch, BranchDTO> {
	List<BranchDTO> findAll();

	void saveBranchsByOrganization(List<BranchDTO> branchs, Organization organization);

	Branch convertDTOtoModelWithOutOrganization(BranchDTO dto);

	BranchDTO convertModelToDTOWithOutOrganization(final Branch model);

	List<BranchDTO> convertEntitiesToDtosWithOutOrganization(List<Branch> list);

	List<Branch> convertDtosToEntitiesWithOutOrganization(List<BranchDTO> list);
	
   void deleteByOrganizationId(Long id);
}
