package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.VehicleDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VehicleService {
    VehicleDto addVehicle (VehicleDto vehicleDto, MultipartFile[] images) throws IOException;
    VehicleDto updateVehicle(Long id, VehicleDto vehicleDto,MultipartFile[] images) throws IOException;
    VehicleDto vehicleGetById(Long id);
    List<VehicleDto> getAllVehicles();
    void deleteVehicleById(Long id);
}
