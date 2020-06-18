package com.learning.service;

import java.util.List;

import com.learning.dto.GroupDTO;
import com.learning.model.Group;

public interface GroupService extends CrudService<Group, GroupDTO> {
	List<GroupDTO> findByOrganization(Long idOrganization);
	List<GroupDTO> findByOrganizationAndLevelAndBranch(Long idOrganization,Long idLevel,Long idBranch);
	
	List<GroupDTO> findByUser(Long idUser);
	boolean existingGroup(String name, Long idLevel, Long idBranch);

	boolean existingGroupById( Long id,String name,Long idLevel,Long idBranch);
	
	Long countByOrganizationAndLevelAndBranch(Long idOrg,Long idLevel,Long idBranch);
}
