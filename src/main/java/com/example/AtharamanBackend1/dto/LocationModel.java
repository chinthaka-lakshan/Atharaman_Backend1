package com.example.AtharamanBackend1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LocationModel {
    private Long id;
    private String mainImage;
    private String extraImage1;
    private String extraImage2;
    private String extraImage3;
    private String extraImage4;
    private String location;
    private String shortDescription;
    private String longDescription;
    private String province;
    private Double latitude;
    private Double longitude;

}
