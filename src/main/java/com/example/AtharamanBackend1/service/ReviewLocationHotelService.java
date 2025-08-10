package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.ReviewLocationHotelDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewLocationHotelService {
    ReviewLocationHotelDto createReview(ReviewLocationHotelDto reviewLocationHotelDto, MultipartFile[] images)throws IOException;
    List<ReviewLocationHotelDto> getAllReview();
    ReviewLocationHotelDto getById(Long id);
    ReviewLocationHotelDto updateReview(Long id,ReviewLocationHotelDto reviewLocationHotelDto,MultipartFile[] images)throws IOException;
    void deleteReview(Long id);
}
