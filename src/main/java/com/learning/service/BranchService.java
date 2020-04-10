package com.learning.service;

import java.util.List;

import com.learning.dto.BranchDTO;
import com.learning.model.Branch;

public interface BranchService extends CrudService<Branch, BranchDTO> {
	List<BranchDTO> findAll();
	
	

}
