package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long>{
	@Query(value = "SELECT * FROM Facture  where num_telephone Like:name or borderaux LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM Facture where  borderaux LIKE :name", nativeQuery = true)
	Page<Facture> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
