package com.learning.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.CountryRepository;
import com.learning.dto.CountryDTO;
import com.learning.model.Country;
import com.learning.model.base.Demande;
import com.learning.model.base.PartialList;
import com.learning.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	private static final String LANG_FR = "fr";
	private static final String LANG_EN = "en";
	private static final String LANG_AR = "ar";
	
	@Override
	public List<Country> getAllCountry() {
		return countryRepository.findAll();
	}

	@Override
	public Country getCountryByCode(String coutryCode) {
		return countryRepository.findByCode(coutryCode);
	}

	@Override
	public List<CountryDTO> getAllCountriesByLang(String lang) {
		
		List<CountryDTO> countries = null;
		
		if (lang.equalsIgnoreCase(LANG_FR)) {
			countries =  countryRepository.findCountriesInFrench();
		} else if (lang.equalsIgnoreCase(LANG_EN)) {
			countries = countryRepository.findCountriesInEnglish();
		} else if (lang.equalsIgnoreCase(LANG_AR)) {
			countries = countryRepository.findCountriesInArab();
		}

		return countries;
	}

	
	public Country convertDTOToEntity(CountryDTO countryDTO) {
		Country country = new Country();
		country.setId(countryDTO.getId());
		return country;

	}

	
	public List<Country> convertListDTOToEntities(List<CountryDTO> listCountryDTO) {
		return listCountryDTO.stream().map(e -> convertDTOToEntity(e)).collect(Collectors.toList());
	}

	
	public CountryDTO convertEntityToDTO(Country country) {
		CountryDTO countryDTO = new CountryDTO();
		countryDTO.setId(country.getId());
		countryDTO.setCode(country.getCode());
		return countryDTO;
	}

	
	public List<CountryDTO> convertEntitiesToDTO(List<Country> listCountry) {
		return listCountry.stream().map(e -> convertEntityToDTO(e)).collect(Collectors.toList());
	}

	@Override
	public CountryDTO getCountryByCodeAndLan(String countryCode, String lang) {
		Country country = getCountryByCode(countryCode);
		CountryDTO countryDto = convertEntityToDTO(country);
		if (lang.equalsIgnoreCase(LANG_FR)) {
			countryDto.setLang(country.getFrenchLang());
			return countryDto;
		} else if (lang.equalsIgnoreCase(LANG_EN)) {
			countryDto.setLang(country.getEnglishLang());
			return countryDto;
		} else if (lang.equalsIgnoreCase(LANG_AR)) {
			countryDto.setLang(country.getArabLang());
			return countryDto;
		}
		return null;
	}

	@Override
	public CountryDTO save(CountryDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryDTO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Country model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartialList<CountryDTO> findByCriteres(Demande<CountryDTO> demande) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country convertDTOtoModel(CountryDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryDTO convertModelToDTO(Country model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialList<CountryDTO> convertToListDTO(PartialList<Country> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CountryDTO> convertEntitiesToDtos(List<Country> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> convertDtosToEntities(List<CountryDTO> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
