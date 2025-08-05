package com.example.AtharamanBackend1.dto;


import lombok.Data;

@Data
public class HotelOwnerRequestDto {
    private Long id;
    private String OwnerName;
    private String hotelOwnerNic;
    private String businessMail;
    private String contactNumber;
    private String description;
    private Long user_id;
}
