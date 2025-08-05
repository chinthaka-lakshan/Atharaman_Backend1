package com.example.AtharamanBackend1.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReviewLocationHotelDto {
    private Long id;
    private Integer rating;
    private String comment;
    private String type;
    private List<String> imagePaths;
    private Long user_id;
}
