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

import java.util.List;
import java.util.stream.Collectors;

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
        vehicleOwner.setVehicleOwnerNic(vehicleOwner.getVehicleOwnerNic());
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

    @Override
    public List<VehicleOwnerDto> getAllVehicleOwners() {
        return vehicleOwnerRepository.findAll().stream()
                .map(this::cobvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleOwnerDto getVehicleOwnerById(Long id) {
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Vehicle Owner Not Found"));
        return cobvertToDto(vehicleOwner);
    }

    @Override
    public VehicleOwnerDto updateVehicleOwner(VehicleOwnerDto vehicleOwnerDto) {
        VehicleOwner vehicleOwner = new VehicleOwner();
        vehicleOwner.setVehicleOwnerName(vehicleOwnerDto.getVehicleOwnerName());
        vehicleOwner.setVehicleOwnerNic(vehicleOwner.getVehicleOwnerNic());
        vehicleOwner.setBusinessMail(vehicleOwnerDto.getBusinessMail());
        vehicleOwner.setPersonalNumber(vehicleOwnerDto.getPersonalNumber());
        vehicleOwner.setWhatsappNumber(vehicleOwnerDto.getWhatsappNumber());
        vehicleOwner.setLocations(vehicleOwnerDto.getLocations());
        vehicleOwner.setDescription(vehicleOwnerDto.getDescription());

        vehicleOwnerRepository.save(vehicleOwner);
        return  cobvertToDto(vehicleOwner);
    }

    @Override
    public void deleteVehicleOwnerById(Long id) {
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Vehicle Owner Not Found"));
        if(vehicleOwner.getUser()!=null){
            User user = vehicleOwner.getUser();
            user.setVehicleOwner(null);
            vehicleOwner.setUser(null);
        }
        vehicleOwnerRepository.delete(vehicleOwner);
    }

    private VehicleOwnerDto cobvertToDto(VehicleOwner vehicleOwner) {
        VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto();
        vehicleOwnerDto.setId(vehicleOwner.getId());
        vehicleOwnerDto.setVehicleOwnerName(vehicleOwner.getVehicleOwnerName());
        vehicleOwnerDto.setVehicleOwnerNic(vehicleOwner.getVehicleOwnerNic());
        vehicleOwnerDto.setBusinessMail(vehicleOwner.getBusinessMail());
        vehicleOwnerDto.setPersonalNumber(vehicleOwner.getPersonalNumber());
        vehicleOwnerDto.setWhatsappNumber(vehicleOwner.getWhatsappNumber());
        vehicleOwnerDto.setLocations(vehicleOwner.getLocations());
        vehicleOwnerDto.setDescription(vehicleOwner.getDescription());
        if(vehicleOwner.getUser()!=null){
            vehicleOwnerDto.setUser_id(vehicleOwner.getUser().getId());
        }else {
            vehicleOwnerDto.setUser_id(null);
        }
        return vehicleOwnerDto;

    }
}
