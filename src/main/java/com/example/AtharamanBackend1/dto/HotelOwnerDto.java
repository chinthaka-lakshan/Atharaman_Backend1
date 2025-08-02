package com.example.AtharamanBackend1.dto;

import lombok.Data;

@Data
public class HotelOwnerDto {
    private Long id;
    private String hotelOwnerName;
    private String hotelOwnerNic;
    private String businessMail;
    private String contactNumber;
    private Long user_id;
}
