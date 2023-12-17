package com.hr.service;

import java.util.List;

import com.hr.entity.Country;

public interface CountrySevice {
	public void addNewCountry(Country country);
	public void updateCountry(Country country);
	public List<Country> getAllCountries();
	public void deleteCountryById(String Id);
	public Country getCountryById(String Id);
}
