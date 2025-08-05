package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.LocationDto;
import com.example.AtharamanBackend1.entity.Location;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.repository.LocationRepository;
import com.example.AtharamanBackend1.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;


    @Override
    public LocationDto createLocation(LocationDto locationDto, MultipartFile[] images) throws IOException {
        Location location = new Location();

        location.setLocationName(locationDto.getLocationName());
        location.setShortDescription(locationDto.getShortDescription());
        location.setLongDescription(locationDto.getLongDescription());
        location.setProvince(locationDto.getProvince());
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/locations/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }
        }
        location.setImagePaths(imagePaths);

        Location savedLocation = locationRepository.save(location);
        locationDto.setId(savedLocation.getId());
        return locationDto;
    }

    @Override
    public List<LocationDto> getAllLocations(){
        return locationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDto getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location Not Found"));
        return convertToDto(location);
    }

    @Override
    public LocationDto updateLocationById(Long id, LocationDto locationDto, MultipartFile[] images) throws IOException {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location Not Found"));

        location.setLocationName(locationDto.getLocationName());
        location.setShortDescription(locationDto.getShortDescription());
        location.setLongDescription(locationDto.getLongDescription());
        location.setProvince(locationDto.getProvince());
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());


        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/locations/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }
            location.setImagePaths(imagePaths);
        }

        Location updated = locationRepository.save(location);
        locationDto.setId(updated.getId());
        return locationDto;
    }

    @Override
    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }

    private LocationDto convertToDto(Location location) {
        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setImagePaths(location.getImagePaths());
        dto.setLocationName(location.getLocationName());
        dto.setShortDescription(location.getShortDescription());
        dto.setLongDescription(location.getLongDescription());
        dto.setProvince(location.getProvince());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());

        return dto;
    }
}
