package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.LocationModel;
import com.example.AtharamanBackend1.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "*") // Optional: for frontend integration
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<LocationModel> getAllLocations() {
        return locationService.getAllLocations();
    }

}
