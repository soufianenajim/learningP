package com.learning.dao;
import java.util.List;

import com.learning.dto.UserDTO;
import com.learning.model.User;
import com.learning.model.base.Demande;

public interface UserRepositorySearchCriteria {
	
	public List<User> findByCriteres(Demande<UserDTO> demande);
	
	public Long countByCriteres(Demande<UserDTO> demande);


}