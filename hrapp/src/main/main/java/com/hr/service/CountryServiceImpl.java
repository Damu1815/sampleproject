package com.hr.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.hr.entity.Country;
import com.hr.exception.CountryNotFoundException;
import com.hr.repository.CountryRepository;
import com.hr.util.ErrorResponse;

@Service
public class CountryServiceImpl implements CountrySevice {

	@Autowired
	CountryRepository countryRepository;

	public void addNewCountry(Country country) {
		Optional<Country> optionalCountry = countryRepository.findById(country.getCountryId());
		if (optionalCountry.isEmpty()) {
			countryRepository.save(country);
		} else {
		  ErrorResponse response = new ErrorResponse(LocalDate.now(),"Country Details Already Exists");
		  throw new CountryNotFoundException(response);
		}
	}

	public void updateCountry(Country country) {
		Optional<Country> optionalCountry = countryRepository.findById(country.getCountryId());
		if (optionalCountry.isPresent()) {
			countryRepository.save(country);
		} else {
		  ErrorResponse response = new ErrorResponse(LocalDate.now(),"Country Not Found");
		  throw new CountryNotFoundException(response);
		}
	}

	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	public void deleteCountryById(String Id) {
			
			Optional<Country> optionalCountry = countryRepository.findById(Id);
			if (optionalCountry.isPresent()) {
				countryRepository.deleteById(Id);
			} else {
			  ErrorResponse response = new ErrorResponse(LocalDate.now(),"Country Not Found");
			  throw new CountryNotFoundException(response);
			}
	}

	public Country getCountryById(String Id) {
		
		Optional<Country> optionalCountry = countryRepository.findById(Id);
		if (optionalCountry.isEmpty()) {
			 ErrorResponse response = new ErrorResponse(LocalDate.now(),"Country Not Found");
			 throw new CountryNotFoundException(response);
		}
			return countryRepository.findById(Id).get(); 
	}
}
