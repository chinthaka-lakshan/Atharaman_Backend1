package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.VehicleOwnerDto;
import com.example.AtharamanBackend1.service.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicleOwners")
public class VehicleOwnerController {
    @Autowired
    VehicleOwnerService vehicleOwnerService;

    @PostMapping
    public VehicleOwnerDto createVehicleOwner(@RequestBody VehicleOwnerDto vehicleOwnerDto){
        return vehicleOwnerService.createVehicleOwner(vehicleOwnerDto);
    }

    @GetMapping
    public List<VehicleOwnerDto> getAllVehicleOwners(){
        return vehicleOwnerService.getAllVehicleOwners();
    }

    @GetMapping("/{id}")
    public VehicleOwnerDto getVehicleOwnerById(@PathVariable Long id){
        return vehicleOwnerService.getVehicleOwnerById(id);
    }

    @PutMapping("/{id}")
    public VehicleOwnerDto updateVehicleOwner(@RequestBody VehicleOwnerDto vehicleOwnerDto, @PathVariable Long id){
        return vehicleOwnerService.updateVehicleOwner(vehicleOwnerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleOwnerById(@PathVariable Long id){
        vehicleOwnerService.deleteVehicleOwnerById(id);
        return ResponseEntity.ok("VehicleOwner deleted successfully");
    }
}
