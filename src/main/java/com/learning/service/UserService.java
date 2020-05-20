package com.learning.service;

import java.util.List;

import com.learning.dto.NotificationDTO;
import com.learning.dto.UserDTO;
import com.learning.exceptions.BusinessException;
import com.learning.model.RoleName;
import com.learning.model.User;
import com.learning.security.services.UserDetailsImpl;

public interface UserService extends CrudService<User, UserDTO> {

	List<UserDTO> findAllProfessorByOrganisation(Long idOrg);

	User convertDTOtoModelWithOutRelation(UserDTO dto);

	UserDTO convertModelToDTOWithOutRelation(final User model);

	List<UserDTO> convertEntitiesToDtosWithOutRelation(List<User> list);

	List<User> convertDtosToEntitiesWithOutRelation(List<UserDTO> list);

	List<UserDTO> findByLevelAndBranch(Long idLevel, Long idBranch);
	
	List<UserDTO> findByGroup(Long idGroup);
	
	List<UserDTO> findByGroupAndRole(Long idGroup,RoleName role);

	UserDTO convertFromUserDetailsToDTO(UserDetailsImpl userDetail,String token);
	
	 UserDTO saveU(UserDTO userDTO) throws BusinessException;
	 
	 NotificationDTO getNotificatonsById(Long id);
	 
	List<UserDTO> findByNameContainingByExam(String name,Long idExam);
}
