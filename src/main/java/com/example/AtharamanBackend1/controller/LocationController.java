package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.LocationDto;
import com.example.AtharamanBackend1.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping(consumes = "multipart/form-data")
    public LocationDto createLocation(
            @RequestPart("location") LocationDto locationDto,
            @RequestPart("images") MultipartFile[] images) throws IOException {
        return locationService.createLocation(locationDto, images);

    }

    @GetMapping
    public List<LocationDto> getAllLocations(){
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public LocationDto getLocation(@PathVariable Long id){
        return locationService.getLocationById(id);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public LocationDto updateLocation(
            @PathVariable Long id,
            @RequestPart ("location") LocationDto locationDto,
            @RequestPart(value = "images", required = false) MultipartFile[] images)throws IOException{
        return locationService.updateLocationById(id, locationDto, images);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id){
        locationService.deleteLocationById(id);
        return ResponseEntity.ok("Location Deleted Success");
    }
}
