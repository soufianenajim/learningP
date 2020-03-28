package com.learning.service;

import java.util.List;

import com.learning.dto.UserDTO;
import com.learning.model.User;

public interface UserService extends CrudService<User, UserDTO> {

	List<UserDTO> findAllProfessor();
}
