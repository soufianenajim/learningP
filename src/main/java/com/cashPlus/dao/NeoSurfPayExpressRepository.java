package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.NeoSurfPayExpress;

public interface NeoSurfPayExpressRepository extends JpaRepository<NeoSurfPayExpress, Long>{
	@Query(value = "SELECT * FROM NeoSurfPayExpress  where  borderaux LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM NeoSurfPayExpress where  borderaux LIKE :name", nativeQuery = true)
	Page<NeoSurfPayExpress> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
