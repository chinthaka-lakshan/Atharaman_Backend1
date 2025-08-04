package com.example.AtharamanBackend1.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemDto {
    private Long id;
    private String itemName;
    private String description;
    private Double price;
    private List<String> imagePaths;
    private Long shop_id;
}
