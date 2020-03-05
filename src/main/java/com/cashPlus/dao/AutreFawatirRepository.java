package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.AutreFawatir;

public interface AutreFawatirRepository extends JpaRepository<AutreFawatir, Long>{

	@Query(value = "SELECT * FROM AutreFawatir  where borderaux like :name or   ref_paiement LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM AutreFawatir  where borderaux like :name or   ref_paiement LIKE :name", nativeQuery = true)
	Page<AutreFawatir> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
