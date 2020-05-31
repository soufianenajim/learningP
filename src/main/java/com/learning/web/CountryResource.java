package com.learning.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.CountryDTO;
import com.learning.model.base.ConstantBase;
import com.learning.service.CountryService;


@RestController
@RequestMapping("/country")
public class CountryResource {
	private static Logger LOGGER = LogManager.getLogger("CountryController");
	@Autowired
	private CountryService countryService;

	
	@GetMapping("/all")
	public ResponseEntity<?> getAllCoutries() {
		try {
			return new ResponseEntity<>(countryService.getAllCountry(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/country/all : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	@GetMapping("/{lang}/all")
	public ResponseEntity<List<CountryDTO>> getAllCoutriesByLanguage(@PathVariable String lang) {
		List<CountryDTO> listCountry = countryService.getAllCountriesByLang(lang);
		if (listCountry != null) {
			return new ResponseEntity<>(listCountry, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	
	@GetMapping("/{code}/{lang}")
	public ResponseEntity<?> getCountryByCode(@PathVariable String code, @PathVariable String lang) {

		try {
			return new ResponseEntity<>(countryService.getCountryByCodeAndLan(code, lang), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Problem occored in api/country/code : {} ", e);
			return new ResponseEntity<>(ConstantBase.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
