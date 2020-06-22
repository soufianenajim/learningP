package com.learning.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.dao.UserRepository;
import com.learning.dto.RoleDTO;
import com.learning.dto.UserDTO;
import com.learning.model.Role;
import com.learning.model.User;
import com.learning.service.UserService;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	// @Autowired
	// private LicenceService licenceService;

	@Override
	public UserDetails loadUserByUsername(String email) {
		final User user = userService.findUser(email);

		if (user == null) {
			throw new UsernameNotFoundException("User '" + email + "' not found");
		}

		
		return org.springframework.security.core.userdetails.User//
				.withUsername(user.getEmail())//
				.password(user.getPassword())//
				.authorities(convertUserRolesToAuthorities(user.getRefRole()))//
				// .accountExpired(user.getOrganization()!=null?
				// !(licenceService.countByOrganizationIdAndIsActive(user.getOrganization().getId(),
				// true) == 1L):true)
				// .accountLocked(user.isLocked())//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();
	}

	private List<GrantedAuthority> convertUserRolesToAuthorities(Role roleRef) {

		List<Role> roles = new ArrayList<>();
		roles.add(roleRef);
		List<GrantedAuthority> list = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString()))
				.filter(Objects::nonNull).collect(Collectors.toList());
		return list;
	}
}
