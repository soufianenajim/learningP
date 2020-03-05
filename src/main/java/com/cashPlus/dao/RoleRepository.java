package com.cashPlus.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cashPlus.model.Role;
import com.cashPlus.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(RoleName roleName);
}
