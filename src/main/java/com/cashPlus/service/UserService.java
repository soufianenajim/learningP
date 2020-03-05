package com.cashPlus.service;

import java.util.List;

import com.cashPlus.dto.UserDTO;
import com.cashPlus.model.User;

public interface UserService extends CrudService<User,UserDTO>{
	public List<UserDTO> findAll();
}
