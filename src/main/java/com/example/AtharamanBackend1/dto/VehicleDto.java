package com.example.AtharamanBackend1.dto;

import lombok.Data;

import java.util.List;

@Data
public class VehicleDto {
    private Long id;
    private String vehicleName;
    private String pricePerDay;
    private String mileagePerDay;
    private String withDriver;
    private String description;
    private List<String> locations;
    private List<String> imagePaths;
    private Long vehicleOwner_id;
    private Long user_id;
}
