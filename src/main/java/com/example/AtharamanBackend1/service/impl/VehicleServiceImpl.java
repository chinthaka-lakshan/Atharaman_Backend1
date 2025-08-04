package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.VehicleDto;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.entity.Vehicle;
import com.example.AtharamanBackend1.entity.VehicleOwner;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.repository.VehicleOwnerRepository;
import com.example.AtharamanBackend1.repository.VehicleRepository;
import com.example.AtharamanBackend1.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Override
    public VehicleDto addVehicle(VehicleDto vehicleDto, MultipartFile[] images) throws IOException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName(vehicleDto.getVehicleName());
        vehicle.setPricePerDay(vehicleDto.getPricePerDay());
        vehicle.setMileagePerDay(vehicleDto.getMileagePerDay());
        vehicle.setWithDriver(vehicleDto.getWithDriver());
        vehicle.setDescription(vehicleDto.getDescription());

        User user =userRepository.findById(vehicleDto.getUser_id())
                .orElseThrow(()-> new RuntimeException("User Not Found"));
        vehicle.setUser(user);

        VehicleOwner vehicleOwner =vehicleOwnerRepository.findById(vehicleDto.getVehicleOwner_id())
                .orElseThrow(()->new RuntimeException("Vehicle Owner Not Found"));
        vehicle.setVehicleOwner(vehicleOwner);

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0){
            String uploadDir = "uploads/vehicle-images/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }
        }
        vehicle.setImagePaths(imagePaths);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        vehicleDto.setId(savedVehicle.getId());
        return vehicleDto;
    }
    @Override
    public VehicleDto vehicleGetById(Long id){
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Vehicle Not Found"));
        return convertToDto(vehicle);
    }

    @Override
    public VehicleDto updateVehicle(Long id,VehicleDto vehicleDto,MultipartFile[] images) throws IOException{
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User Not Found"));

        vehicle.setVehicleName(vehicleDto.getVehicleName());
        vehicle.setPricePerDay(vehicleDto.getPricePerDay());
        vehicle.setMileagePerDay(vehicleDto.getMileagePerDay());
        vehicle.setWithDriver(vehicleDto.getWithDriver());
        vehicle.setDescription(vehicleDto.getDescription());

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/vehicle-images/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }

            vehicle.setImagePaths(imagePaths);
        }

        Vehicle updated = vehicleRepository.save(vehicle);
        vehicleDto.setId(updated.getId());
        return vehicleDto;
    }

    public VehicleDto convertToDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setVehicleName(vehicle.getVehicleName());
        dto.setPricePerDay(vehicle.getPricePerDay());
        dto.setMileagePerDay(vehicle.getMileagePerDay());
        dto.setWithDriver(vehicle.getWithDriver());
        dto.setDescription(vehicle.getDescription());
        dto.setImagePaths(vehicle.getImagePaths());

        if (vehicle.getUser() != null) {
            dto.setUser_id(vehicle.getUser().getId());
        }else {
            dto.setUser_id(null);
        }
        if (vehicle.getVehicleOwner() != null) {
            dto.setVehicleOwner_id(vehicle.getVehicleOwner().getId());
        }else {
            dto.setVehicleOwner_id(null);
        }

        return dto;
    }

}
