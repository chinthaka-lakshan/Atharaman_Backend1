package com.example.AtharamanBackend1.dto;

import lombok.Data;

@Data
public class OtherReviewDto {
    private Long id;
    private Integer rating;
    private String comment;
    private String type;
    private Long user_id;
}
