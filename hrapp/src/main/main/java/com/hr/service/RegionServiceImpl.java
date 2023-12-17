package com.hr.service;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.hr.entity.Region;
import com.hr.exception.DuplicateRegionException;
import com.hr.exception.RegionNotFoundException;
import com.hr.repository.RegionRepository;
import com.hr.util.ErrorResponse;
 
@Service
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionRepository regionRepository;
 
	@Override
	public boolean saveRegion(Region region) {
		Optional<Region> opt = regionRepository.findById(region.getRegionId());
		if(opt.isPresent()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Region Already Exists");
			throw new DuplicateRegionException(response);
		}
		regionRepository.save(region);
		return true;
	}
 
	@Override
	public boolean updateRegion(Region region) {
		Optional<Region> opt = regionRepository.findById(region.getRegionId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Region Not Found");
			throw new RegionNotFoundException(response);
		}
		regionRepository.save(region);
		return true;
	}
 
	@Override
	public List<Region> getAllRegions() {
		List<Region> region = regionRepository.findAll();
		if(region.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Region cannot be deleted");
			throw new RegionNotFoundException(response);
		}
				return regionRepository.findAll();
	}
 
	@Override
	public void deleteRegionById(Long Id) {
		   Optional<Region> region = regionRepository.findById(Id);
		    if (region.isEmpty()) {
		        ErrorResponse response = new ErrorResponse(LocalDate.now(),"Region cannot be deleted");
		       throw new RegionNotFoundException(response);
		    }
		    regionRepository.delete(region.get());
	}
 
	@Override
	public Region getRegionById(Long Id) {
		Optional<Region> region = regionRepository.findById(Id);
		if(region.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"RegionById Not Found");
			throw new RegionNotFoundException(response);
		}
		return regionRepository.findById(Id).get();
	}
 
}
