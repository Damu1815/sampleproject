package com.hr.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hr.entity.Country;
import com.hr.repository.CountryRepository;
import com.hr.service.CountrySevice;

@RequestMapping("/api/v1")
@RestController
public class CountryController {


	@Autowired
	private CountrySevice countryService;

	@PostMapping("/country")
	public ResponseEntity<String> addNewCountry(@RequestBody Country country) {
		countryService.addNewCountry(country);
		return new ResponseEntity<String>("Country Saved Successfully", HttpStatus.OK);
	}

	@PutMapping("/country")
	public ResponseEntity<String> updateCountry(@RequestBody Country country) {
		countryService.updateCountry(country);
		return new ResponseEntity<String>("Country Updated Successfully", HttpStatus.OK);
	}

	@GetMapping("/country")
	public ResponseEntity<List<Country>> getAll() {
		return new ResponseEntity<List<Country>>(countryService.getAllCountries(), HttpStatus.OK);
	}

	@GetMapping("/country/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable String Id) {
		return new ResponseEntity<>(countryService.getCountryById(Id), HttpStatus.OK);
	}

	@DeleteMapping("/country/{id}")
	public ResponseEntity<String> deleteCountryById(@PathVariable String id) {
		countryService.deleteCountryById(id);
		return new ResponseEntity<>("Country Deleted Succesfully",HttpStatus.OK);
	
	}

}
