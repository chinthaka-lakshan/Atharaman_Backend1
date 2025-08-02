package com.example.AtharamanBackend1.dto;

import lombok.Data;

import java.util.List;

@Data
public class VehicleOwnerDto {
    private Long id;
    private String vehicleOwnerName;
    private String businessMail;
    private String personalNumber;
    private String whatsappNumber;
    private List<String> locations;
    private String description;
    private Long user_id;
}
