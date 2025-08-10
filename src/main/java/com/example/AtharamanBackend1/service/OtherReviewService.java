package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.OtherReviewDto;

import java.util.List;

public interface OtherReviewService {
    OtherReviewDto createReview(OtherReviewDto otherReviewDto);
    List<OtherReviewDto> getAllReview();
    OtherReviewDto getById(Long id);
    OtherReviewDto updateReview(Long id, OtherReviewDto otherReviewDto);
    void deleteReview(Long id);
}
