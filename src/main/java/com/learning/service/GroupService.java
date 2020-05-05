package com.learning.service;

import java.util.List;

import com.learning.dto.GroupDTO;
import com.learning.model.Group;

public interface GroupService extends CrudService<Group, GroupDTO> {
	List<GroupDTO> findByOrganization(Long idOrganization);
	boolean existingGroup(String name, Long idLevel, Long idBranch);

	boolean existingGroupById( Long id,String name,Long idLevel,Long idBranch);
}
