package com.learning.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.learning.dto.NotificationDTO;
import com.learning.dto.UserDTO;
import com.learning.exceptions.BusinessException;
import com.learning.model.RoleName;
import com.learning.model.StatutEnum;
import com.learning.model.User;

public interface UserService extends CrudService<User, UserDTO> {

	List<UserDTO> findAllProfessorByOrganisation(Long idOrg);

	List<UserDTO> findAllByOrganisationWithoutUser(Long idOrg, Long idUser);

	User convertDTOtoModelWithOutRelation(UserDTO dto);

	UserDTO convertModelToDTOWithOutRelation(final User model);

	List<UserDTO> convertEntitiesToDtosWithOutRelation(List<User> list);

	List<User> convertDtosToEntitiesWithOutRelation(List<UserDTO> list);

	List<UserDTO> findByLevelAndBranch(Long idLevel, Long idBranch);

	List<UserDTO> findByGroup(Long idGroup);

	List<UserDTO> findByGroupAndRole(Long idGroup, RoleName role);

	// UserDTO convertFromUserDetailsToDTO(UserDetailsImpl userDetail, String
	// token);

	UserDTO saveU(UserDTO userDTO) throws BusinessException;

	NotificationDTO getNotificatonsById(Long id);

	List<UserDTO> findByNameContainingByExam(String name, Long idExam);

	Long countStudentByTeacherAndGroup(Long idTeacher, Long idGroup);

	// UserDetailsImpl getUserPrincipal();

	List<UserDTO> findCatchingUpStudentByModule(Long idModule, StatutEnum statut);

	User findUserById(Long id);

	Long countUserByOrganizationAndLevelAndBranch(Long idOrg, Long idLevel, Long idBranch, Long idGroup, String type);

	User addTokenToUser(String username, String token, String oldToken);

	User findUser(String email);

	User findUserByToken(String token);

	User findUserByOldToken(String oldToken);

	UserDetails getUserPrincipal();

	void logout(String username);
	
	List<Object> countOnlineUserByOrganization(Long idOrg);
}
