package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.entity.Country;
import com.hr.repository.CountryRepository;

@RestController
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;
    
   @GetMapping("/allcountries")
   public List<Country> getAll(){
	   return countryRepository.findAll();
   }
}
