package com.learning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.dto.CountryDTO;
import com.learning.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	public Country findByCode(String couontryCode);

	@Query("SELECT NEW com.learning.dto.CountryDTO(c.id, c.code, c.englishLang) FROM Country c")
	public List<CountryDTO> findCountriesInEnglish();

	@Query("SELECT NEW com.learning.dto.CountryDTO(c.id, c.code, c.frenchLang) FROM Country c")
	public List<CountryDTO> findCountriesInFrench();

	@Query("SELECT NEW com.learning.dto.CountryDTO(c.id, c.code, c.arabLang) FROM Country c")
	public List<CountryDTO> findCountriesInArab();

}
