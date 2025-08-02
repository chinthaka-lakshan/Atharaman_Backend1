package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.VehicleOwnerDto;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.entity.VehicleOwner;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.repository.VehicleOwnerRepository;
import com.example.AtharamanBackend1.service.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleOwnerServiceImpl implements VehicleOwnerService {
    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public VehicleOwnerDto createVehicleOwner(VehicleOwnerDto vehicleOwnerDto) {
        VehicleOwner vehicleOwner = new VehicleOwner();
        vehicleOwner.setVehicleOwnerName(vehicleOwnerDto.getVehicleOwnerName());
        vehicleOwner.setBusinessMail(vehicleOwnerDto.getBusinessMail());
        vehicleOwner.setPersonalNumber(vehicleOwnerDto.getPersonalNumber());
        vehicleOwner.setWhatsappNumber(vehicleOwnerDto.getWhatsappNumber());
        vehicleOwner.setLocations(vehicleOwnerDto.getLocations());
        vehicleOwner.setDescription(vehicleOwnerDto.getDescription());

        User user = userRepository.findById(vehicleOwnerDto.getUser_id())
                .orElseThrow(()-> new RuntimeException("User Not Found"));
        vehicleOwner.setUser(user);

        VehicleOwner savedVehicleOwner = vehicleOwnerRepository.save(vehicleOwner);
        vehicleOwnerDto.setId(savedVehicleOwner.getId());
        return vehicleOwnerDto;
    }
}
