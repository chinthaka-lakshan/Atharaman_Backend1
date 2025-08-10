package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.dto.ReviewLocationHotelDto;
import com.example.AtharamanBackend1.service.ReviewLocationHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/review_L&H")
public class ReviewLocationHotelController {

    @Autowired
    private ReviewLocationHotelService reviewLocationHotelService;

    @PostMapping(consumes = "multipart/form-data")
    public ReviewLocationHotelDto createReview(
            @RequestPart("reviewLocationHotel") ReviewLocationHotelDto reviewLocationHotelDto,
            @RequestPart("images") MultipartFile[] images) throws IOException {
        return reviewLocationHotelService.createReview(reviewLocationHotelDto, images);
    }

    @GetMapping
    public List<ReviewLocationHotelDto> getAllReview(){
        return reviewLocationHotelService.getAllReview();
    }

    @GetMapping("/{id}")
    public ReviewLocationHotelDto getById(@PathVariable Long id){
        return reviewLocationHotelService.getById(id);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ReviewLocationHotelDto updateReview(
            @PathVariable Long id,
            @RequestPart ("reviewLocationHotel") ReviewLocationHotelDto reviewLocationHotelDto,
            @RequestPart(value = "images", required = false) MultipartFile[] images) throws IOException{
        return reviewLocationHotelService.updateReview(id,reviewLocationHotelDto, images);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id){
        reviewLocationHotelService.deleteReview(id);
        return ResponseEntity.ok("Guide Deleted Success");
    }
}
