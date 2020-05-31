package com.learning.service;

import java.util.List;

import com.learning.dto.CountryDTO;
import com.learning.model.Country;

public interface CountryService extends CrudService<Country, CountryDTO> {

	public List<Country> getAllCountry();

	public List<CountryDTO> getAllCountriesByLang(String lang);

	public Country getCountryByCode(String coutryCode);
	
	public CountryDTO getCountryByCodeAndLan(String countryCode, String lang);

}
