package com.example.AtharamanBackend1.dto;

import lombok.Data;

import java.util.List;

@Data
public class GuideDto {
    private Long id;
    private String guideName;
    private String businessMail;
    private String personalNumber;
    private String whatsappNumber;
    private List<String> languages;
    private String description;
    private List<String> locations;
    private List<String> imagePaths;
    private Long user_id;
}
