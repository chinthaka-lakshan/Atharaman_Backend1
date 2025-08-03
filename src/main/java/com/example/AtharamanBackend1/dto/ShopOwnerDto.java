package com.example.AtharamanBackend1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ShopOwnerDto {
    private Long id;
    private String shopOwnerName;
    private String shopOwnerNic;
    private String businessMail;
    private String contactNumber;
    private Long user_id;
}

