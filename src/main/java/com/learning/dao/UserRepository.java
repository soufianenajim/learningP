package com.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.model.User;

public interface UserRepository extends JpaRepository<User,Long > {

}
