package com.hr.service;

import java.util.List;

import com.hr.entity.Location;

public interface LocationService {

	public void addNewLocation(Location location);
	public void updateLocation(Long locationId, Long newLocationId);
	public List<Location> findAllLocations();
	public void deleteLocationById(Long locationId);
	public Location findById(Long locationId);
}