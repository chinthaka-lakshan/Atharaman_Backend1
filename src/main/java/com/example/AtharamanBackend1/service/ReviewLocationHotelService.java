package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.ReviewLocationHotelDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReviewLocationHotelService {
    ReviewLocationHotelDto createReview(ReviewLocationHotelDto reviewLocationHotelDto, MultipartFile[] images)throws IOException;
}
