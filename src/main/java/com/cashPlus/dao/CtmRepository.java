package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.Ctm;

public interface CtmRepository extends JpaRepository<Ctm, Long> {
	@Query(value = "SELECT * FROM Ctm  where borderaux like :name or   numCtm LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM Ctm where borderaux like :name or   numCtm LIKE :name", nativeQuery = true)
	Page<Ctm> findByCriters(Pageable pageable, @Param("name") @NotNull String name);
}
