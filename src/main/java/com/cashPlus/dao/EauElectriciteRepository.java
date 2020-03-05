package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.EauElectricite;

public interface EauElectriciteRepository extends JpaRepository<EauElectricite, Long>{
	@Query(value = "SELECT * FROM EauElectricite  where borderaux like :name or  numFacture LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM EauElectricite where borderaux like :name or  numFacture LIKE :name", nativeQuery = true)
	Page<EauElectricite> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
