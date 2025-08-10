package com.example.AtharamanBackend1.dto;


import lombok.Data;

@Data
public class ShopOwnerRequestDto {
    private Long id;
    private String shopOwnerName;
    private String shopOwnerNic;
    private String businessMail;
    private String contactNumber;
    private Long user_id;

}
