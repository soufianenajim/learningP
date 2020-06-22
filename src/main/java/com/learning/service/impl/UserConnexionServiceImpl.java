package com.learning.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.learning.dao.UserConnexionRepository;
import com.learning.dto.StatistiqueOrgConnexionDTO;
import com.learning.dto.UserConnexionDTO;
import com.learning.dto.UserDTO;
import com.learning.model.User;
import com.learning.model.UserConnexion;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.UserConnexionService;
import com.learning.service.UserService;

@Service
public class UserConnexionServiceImpl implements UserConnexionService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserConnexionRepository userConnexionRepository;





	@Async
	@Override
	public void save(UserConnexion userConnexion) {

		UserConnexion existedUserConnexion = userConnexionRepository.findByUserAndDate(userConnexion.getUser().getId(),
				userConnexion.getDateConnexion());
		if (existedUserConnexion != null) {
			int numberConnexion = existedUserConnexion.getNumberConnexion() + 1;
			existedUserConnexion.setNumberConnexion(numberConnexion);
			userConnexionRepository.save(existedUserConnexion);

		} else {
			userConnexion.setNumberConnexion(1);
			userConnexionRepository.save(userConnexion);

		}

	}

	@Override
	public List<StatistiqueOrgConnexionDTO> getConnexionByPeriod(String period) {
		LocalDate startDate = generatDateByPeriod(period);
		return userConnexionRepository.getConnexionByOrganizationAfterDate(startDate,PageRequest.of(0, 5)).stream()
				.map(e -> convertObjecToStatistiqueDTO(e)).collect(Collectors.toList());

	}

	private StatistiqueOrgConnexionDTO convertObjecToStatistiqueDTO(Object object) {
		Object[] objectArray = (Object[]) object;
		final Long idOrg = (Long) objectArray[0];
		final String nameOrg = (String) objectArray[1];
		final Long numberConnexion = (Long) objectArray[2];
		return new StatistiqueOrgConnexionDTO(idOrg, nameOrg, numberConnexion);
	}

	private LocalDate generatDateByPeriod(String period) {
		LocalDate curreDate = LocalDate.now();
		switch (period) {
		case "day":

			return curreDate.minusDays(1);
		case "week":
			return curreDate.minusWeeks(1);
		case "month":

			return curreDate.minusMonths(1);
		case "trimester":

			return curreDate.minusMonths(3);
		case "semester":

			return curreDate.minusMonths(6);
		case "year":

			return curreDate.minusYears(1);
		default:
			return curreDate.minusDays(1);
		}

	}

	@Override
	public List<UserDTO> getConnexionByPeriodAndOrganization(String period, Long idOrg) {
		LocalDate startDate = generatDateByPeriod(period);
		return userConnexionRepository.getConnexionByUserAfterDate(startDate, idOrg).stream()
				.map(e -> convertObjecToUserDTO(e)).collect(Collectors.toList());
	}

	private UserDTO convertObjecToUserDTO(Object object) {
		Object[] objectArray = (Object[]) object;
		final User user = (User) objectArray[0];
		final Long numberConnexion = (Long) objectArray[1];
		UserDTO userDTO = userService.convertModelToDTO(user);
		userDTO.setNumberConnexion(numberConnexion);
		return userDTO;
	}

	@Override
	public UserConnexionDTO save(UserConnexionDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserConnexionDTO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UserConnexion model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<UserConnexionDTO> findByCriteres(Demande<UserConnexionDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserConnexion convertDTOtoModel(UserConnexionDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserConnexionDTO convertModelToDTO(UserConnexion userConnexion) {
		UserConnexionDTO userConnexionDTO = new UserConnexionDTO();
		userConnexionDTO.setId(userConnexion.getId());
		userConnexionDTO.setUser(userService.convertModelToDTO(userConnexion.getUser()));
		userConnexionDTO.setNumberConnexion(userConnexion.getNumberConnexion());
		userConnexionDTO.setDateConnexion(userConnexion.getDateConnexion().toString());
		return userConnexionDTO;
	}

	@Override
	public PartialList<UserConnexionDTO> convertToListDTO(PartialList<UserConnexion> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserConnexionDTO> convertEntitiesToDtos(List<UserConnexion> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserConnexion> convertDtosToEntities(List<UserConnexionDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
