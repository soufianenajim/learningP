package com.cashPlus.dao;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM User  where  last_name LIKE :name or first_name like :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM User where  last_name LIKE :name or first_name like :name", nativeQuery = true)
	Page<User> findByCriters(Pageable pageable, @Param("name") @NotNull String name);

	Optional<User> findByLogin(String login);

	Boolean existsByLogin(String login);

	
}
