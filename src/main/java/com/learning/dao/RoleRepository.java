package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long > {

}
