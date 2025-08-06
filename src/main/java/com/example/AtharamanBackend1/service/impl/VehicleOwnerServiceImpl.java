package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.VehicleOwnerDto;
import com.example.AtharamanBackend1.dto.VehicleOwnerRequestDto;
import com.example.AtharamanBackend1.entity.*;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.repository.VehicleOwnerRepository;
import com.example.AtharamanBackend1.repository.VehicleOwnerRequestRepository;
import com.example.AtharamanBackend1.service.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleOwnerServiceImpl implements VehicleOwnerService {

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Autowired
    private VehicleOwnerRequestRepository vehicleOwnerRequestRepository;

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




    @Override
    public void submitVehicleOwnerRequest(VehicleOwnerRequestDto dto) {
        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        VehicleOwnerRequest request = new VehicleOwnerRequest();
        request.setId(dto.getId());
        request.setVehicleOwnerName(dto.getVehicleOwnerName());
        request.setPersonalNumber(dto.getPersonalNumber());
        request.setBusinessMail(dto.getBusinessMail());
        request.setWhatsappNumber(dto.getWhatsappNumber());
        request.setDescription(dto.getDescription());
        request.setLocations(dto.getLocations());
        request.setUser(user);
        request.setStatus(RequestStatus.PENDING);

        vehicleOwnerRequestRepository.save(request);
    }





    @Override
    public void approveVehicleOwnerRequest(Long requestId) {
        VehicleOwnerRequest request = vehicleOwnerRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Vehicle Owner request not found"));

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Request is already processed");
        }

        VehicleOwner vehicleOwner = new VehicleOwner();
        //vehicleOwner.setId(request.getId());
        vehicleOwner.setUser(request.getUser());
        vehicleOwner.setVehicleOwnerName(request.getVehicleOwnerName());
        vehicleOwner.setBusinessMail(request.getBusinessMail());
        vehicleOwner.setPersonalNumber(request.getPersonalNumber());
        vehicleOwner.setWhatsappNumber(request.getWhatsappNumber());
        vehicleOwner.setLocations(request.getLocations());
        vehicleOwner.setDescription(request.getDescription());

        vehicleOwnerRepository.save(vehicleOwner);

        request.setStatus(RequestStatus.APPROVED);
        vehicleOwnerRequestRepository.save(request);


    }

    @Override
    public List<VehicleOwnerRequestDto> getPendingVehicleOwnerRequests() {
        List<VehicleOwnerRequest> requests = vehicleOwnerRequestRepository.findByStatus(RequestStatus.PENDING);

        return requests.stream().map(req -> {
            VehicleOwnerRequestDto dto = new VehicleOwnerRequestDto();
            dto.setUser_id(req.getUser().getId());
            dto.setId(req.getId());
            dto.setVehicleOwnerName(req.getVehicleOwnerName());
            dto.setWhatsappNumber(req.getWhatsappNumber());
            dto.setBusinessMail(req.getBusinessMail());
            dto.setPersonalNumber(req.getPersonalNumber());
            dto.setLocations(req.getLocations());
            dto.setDescription(req.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

}
