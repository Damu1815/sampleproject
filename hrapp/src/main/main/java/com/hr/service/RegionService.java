package com.hr.service;
 
import java.util.List;
 
import com.hr.entity.Region;
 
public interface RegionService {
 
	 boolean saveRegion(Region region);
	 boolean updateRegion(Region region);
	 List<Region> getAllRegions();
	 void deleteRegionById(Long Id);
	 Region getRegionById(Long Id);
}