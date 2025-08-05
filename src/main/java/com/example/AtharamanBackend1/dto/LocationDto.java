package com.example.AtharamanBackend1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class LocationDto {
    private Long id;
    private List<String> imagePaths;
    private String locationName;
    private String shortDescription;
    private String longDescription;
    private String province;
    private Double latitude;
    private Double longitude;

}
