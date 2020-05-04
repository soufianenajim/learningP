package com.learning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long > {

	Role findByName(String name);
	
	@Query("Select r from Role r where r.isClient=?1")
	List<Role> findAllClient(boolean isClient);
}
