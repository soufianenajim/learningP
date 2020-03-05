package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.In;
import com.cashPlus.model.Out;

public interface InRepository extends JpaRepository<In, Long>{
	@Query(value = "SELECT * FROM In  where  borderaux LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM In where borderaux LIKE :name", nativeQuery = true)
	Page<In> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
