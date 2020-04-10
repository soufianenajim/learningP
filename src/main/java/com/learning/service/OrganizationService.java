package com.learning.service;

import java.util.List;

import com.learning.dto.OrganizationDTO;
import com.learning.model.Organization;

public interface OrganizationService extends CrudService<Organization, OrganizationDTO> {
	List<OrganizationDTO> findAll();
	
	

}
