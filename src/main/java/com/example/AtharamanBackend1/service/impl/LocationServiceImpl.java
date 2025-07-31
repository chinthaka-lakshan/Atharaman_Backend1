package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.entity.Location;
import com.example.AtharamanBackend1.dto.LocationDto;
import com.example.AtharamanBackend1.repository.LocationRepository;
import com.example.AtharamanBackend1.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<LocationDto> getAllLocations() {
        List<Location> locations = locationRepository.findAll();

        return locations.stream().map(location -> {
            LocationDto model = new LocationDto();
            model.setId(location.getId());
            model.setMainImage(location.getMainImage());
            model.setExtraImage1(location.getExtraImage1());
            model.setExtraImage2(location.getExtraImage2());
            model.setExtraImage3(location.getExtraImage3());
            model.setExtraImage4(location.getExtraImage4());
            model.setLocation(location.getLocation());
            model.setShortDescription(location.getShortDescription());
            model.setLongDescription(location.getLongDescription());
            model.setProvince(location.getProvince());
            model.setLatitude(location.getLatitude());
            model.setLongitude(location.getLongitude());
            return model;
        }).collect(Collectors.toList());
    }
}
