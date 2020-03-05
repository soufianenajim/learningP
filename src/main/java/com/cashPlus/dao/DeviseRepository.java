package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.Devise;

public interface DeviseRepository extends JpaRepository<Devise, Long>{
	@Query(value = "SELECT * FROM Devise  where borderaux like :name or  qualiteClient LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM Devise where borderaux like :name or  qualiteClient LIKE :name", nativeQuery = true)
	Page<Devise> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
