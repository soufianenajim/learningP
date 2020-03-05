package com.cashPlus.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cashPlus.dao.UserRepository;
import com.cashPlus.dto.UserDTO;
import com.cashPlus.model.User;
import com.cashPlus.model.base.PartialList;
import com.cashPlus.service.RoleService;
import com.cashPlus.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRpository;

	@Autowired
	RoleService roleService;

	@Override
	public User save(User user) {

		return userRpository.save(user);
	}

	@Override
	public User findById(long idUser) {

		return userRpository.findById(idUser).isPresent() ? userRpository.findById(idUser).get() : null;
	}

	@Override
	public void delete(User u) {
		userRpository.deleteById(u.getId());

	}

	@Override
	public PartialList<UserDTO> findByCriteres(Pageable page, String name) {
		Page<User> resultat;
		if (name.equals("") && name.length() == 0) {
			resultat = userRpository.findAll(page);
		} else {
			resultat = userRpository.findByCriters(page, name);
		}

		return convertToListDTO(new PartialList<>(resultat.getTotalElements(), resultat.getContent()));
	}

	@Override
	public User convertDTOtoModel(final UserDTO u) {
		return new User(u.getId(), u.getLogin(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getToken(),
				u.getTokenDate(), u.getIsOnline(), u.getIsOffline(),
				u.getRefRole() != null ? roleService.convertDTOtoModel(u.getRefRole()) : null);

	}

	public UserDTO convertModelToDTO(final User u) {
		return new UserDTO(u.getId(), u.getCreatedAt(), u.getUpdatedAt(), u.getLogin(), u.getPassword(),
				u.getFirstName(), u.getLastName(), u.getToken(), u.getTokenDate(), u.getIsOnline(), u.getIsOffline(),
				u.getRefRole() != null ? roleService.convertModelToDTO(u.getRefRole()) : null);

	}

	@Override
	public PartialList<UserDTO> convertToListDTO(PartialList<User> list) {
		return new PartialList<>(list.getCount(),
				list.getLignes().stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList()));

	}

	private List<UserDTO> convertToListDTO1(List<User> users) {
		return users.stream().map(e -> convertModelToDTO(e)).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> findAll() {
		List<User> users = userRpository.findAll();
		return convertToListDTO1(users);
	}

}
