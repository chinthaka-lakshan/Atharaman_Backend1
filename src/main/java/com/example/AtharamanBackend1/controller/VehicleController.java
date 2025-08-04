package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.VehicleDto;
import com.example.AtharamanBackend1.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = "multipart/form-data")
    public VehicleDto addVehicle(
            @RequestPart("vehicle") VehicleDto vehicleDto,
            @RequestPart("images")MultipartFile[] images) throws IOException{
        return vehicleService.addVehicle(vehicleDto, images);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public VehicleDto updateVehicle(
            @PathVariable Long id,
            @RequestPart("vehicle") VehicleDto vehicleDto,
            @RequestPart(value = "images", required = false) MultipartFile[] images) throws IOException {
        return vehicleService.updateVehicle(id, vehicleDto, images);
    }
    @GetMapping("/{id}")
    public VehicleDto vehicleGetById(@PathVariable Long id){
        return vehicleService.vehicleGetById(id);
    }



}
