package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.AutreTransaction;

public interface AutreTransactionRepository extends JpaRepository<AutreTransaction, Long>{
	@Query(value = "SELECT * FROM AutreTransaction  where borderaux like :name or  info LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM AutreTransaction  where borderaux like :name or  info LIKE :name", nativeQuery = true)
	Page<AutreTransaction> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
