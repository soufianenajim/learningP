package com.learning.service;


import java.util.List;

import com.learning.dto.StatistiqueOrgConnexionDTO;
import com.learning.dto.UserConnexionDTO;
import com.learning.dto.UserDTO;
import com.learning.model.UserConnexion;

public interface UserConnexionService extends CrudService<UserConnexion, UserConnexionDTO> {

	void save(UserConnexion userConnexion);

	List<StatistiqueOrgConnexionDTO> getConnexionByPeriod(String period);
	
	List<UserDTO> getConnexionByPeriodAndOrganization(String period,Long idOrg);

}
