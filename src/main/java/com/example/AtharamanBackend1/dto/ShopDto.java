package com.example.AtharamanBackend1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
public class ShopDto {
    private Long id;
    private String shopName;
    private List<String> location;
    private String description;
    private List<String> imagePaths;
    private Long user_id;
    private Long shopOwner_id;

}
