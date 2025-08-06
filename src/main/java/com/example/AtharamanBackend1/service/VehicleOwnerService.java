package com.example.AtharamanBackend1.service;


import com.example.AtharamanBackend1.dto.VehicleOwnerDto;
import com.example.AtharamanBackend1.dto.VehicleOwnerRequestDto;

import java.util.List;

public interface VehicleOwnerService {
    VehicleOwnerDto createVehicleOwner(VehicleOwnerDto vehicleOwnerDto);
    List<VehicleOwnerDto> getAllVehicleOwners();
    VehicleOwnerDto getVehicleOwnerById(Long id);
    VehicleOwnerDto updateVehicleOwner(VehicleOwnerDto vehicleOwnerDto);
    void deleteVehicleOwnerById(Long id);
    void submitVehicleOwnerRequest(VehicleOwnerRequestDto dto);
    void approveVehicleOwnerRequest(Long requestId);
    List<VehicleOwnerRequestDto> getPendingVehicleOwnerRequests();
}
