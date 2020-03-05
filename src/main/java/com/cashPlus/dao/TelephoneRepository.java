package com.cashPlus.dao;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashPlus.model.Telephone;

public interface TelephoneRepository extends JpaRepository<Telephone, Long>{
	@Query(value = "SELECT * FROM Telephone  where num_telephone like :name or  borderaux LIKE :name   ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM Telephone where numTelephone like :name  or borderaux LIKE :name", nativeQuery = true)
	Page<Telephone> findByCriters(Pageable pageable,@Param("name")@NotNull String name);
}
