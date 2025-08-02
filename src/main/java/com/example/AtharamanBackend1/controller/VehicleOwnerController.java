package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.VehicleOwnerDto;
import com.example.AtharamanBackend1.service.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicleOwners")
public class VehicleOwnerController {
    @Autowired
    VehicleOwnerService vehicleOwnerService;

    @PostMapping
    public VehicleOwnerDto createVehicleOwner(@RequestBody VehicleOwnerDto vehicleOwnerDto){
        return vehicleOwnerService.createVehicleOwner(vehicleOwnerDto);
    }
}
