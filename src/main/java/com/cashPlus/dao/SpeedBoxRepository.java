package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.SpeedBox;

public interface SpeedBoxRepository extends JpaRepository<SpeedBox, Long>{
	@Query(value = "SELECT * FROM SpeedBox  where numColis like :name or  borderaux LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM SpeedBox where numColis like :name  or borderaux LIKE :name", nativeQuery = true)
	Page<SpeedBox> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
