package com.learning.dao;
import java.util.List;

import com.learning.dto.GroupDTO;
import com.learning.model.Group;
import com.learning.model.base.Demande;

public interface GroupRepositorySearchCriteria {
	
	public List<Group> findByCriteres(Demande<GroupDTO> demande);
	
	public Long countByCriteres(Demande<GroupDTO> demande);


}