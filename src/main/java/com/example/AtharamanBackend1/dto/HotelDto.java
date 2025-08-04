package com.example.AtharamanBackend1.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelDto {
    private Long id;
    private String hotelName;
    private String businessMail;
    private String contactNumber;
    private String whatsappNumber;
    private String nicNumber;
    private List<String> locations;
    private List<String> imagePaths;
    private Long hotelOwner_id;
    private Long user_id;


}
