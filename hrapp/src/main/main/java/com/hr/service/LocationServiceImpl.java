package com.hr.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.entity.Country;
import com.hr.entity.Location;
import com.hr.exception.LocationNotFoundException;
import com.hr.repository.CountryRepository;
import com.hr.repository.LocationRepository;
import com.hr.util.ErrorResponse;

@Service
public class LocationServiceImpl implements LocationService {


	@Autowired
	LocationRepository locationRepository;

	public Location findById(Long locationId) {
		Optional<Location> optional = locationRepository.findById(locationId);
		if(optional.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Location Not Found");
			throw new LocationNotFoundException(response);
		}
		
		return optional.get();
		
	}

	@Override
	public List<Location> findAllLocations() {
		return locationRepository.findAll();
	}

	@Override
	public void addNewLocation(Location location) {
			Optional<Location> optional = locationRepository.findById(location.getLocationId());
			if(optional.isPresent()) {
				ErrorResponse response = new ErrorResponse(LocalDate.now(),"Location Details already exists");
				throw new LocationNotFoundException(response);
				
			}
			locationRepository.save(location);	
	}



	@Override

		public void deleteLocationById(Long locationId) {
          Optional<Location> location = locationRepository.findById(locationId);
            if(location.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Location Details already exists");
				throw new LocationNotFoundException(response);

			}
             locationRepository.delete(location.get());

	}

	@Override
	public void updateLocation(Long locationId, Long newLocationId) {
		Optional<Location> location = locationRepository.findById(locationId);
		if(location.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Location Details already exists");
			throw new LocationNotFoundException(response);
		}
		location.get().setLocationId(newLocationId);
		locationRepository.save(location.get());
	}

}
