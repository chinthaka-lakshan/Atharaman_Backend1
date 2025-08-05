package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.ReviewLocationHotelDto;
import com.example.AtharamanBackend1.entity.Guide;
import com.example.AtharamanBackend1.entity.ReviewLocationHotel;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.repository.ReviewLocationHotelRepository;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.service.ReviewLocationHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewLocationHotelServiceImpl implements ReviewLocationHotelService {

    @Autowired
    private ReviewLocationHotelRepository reviewLocationHotelRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReviewLocationHotelDto createReview (ReviewLocationHotelDto reviewLocationHotelDto, MultipartFile[] images)throws IOException{
        ReviewLocationHotel reviewLocationHotel = new ReviewLocationHotel();
        reviewLocationHotel.setRating(reviewLocationHotelDto.getRating());
        reviewLocationHotel.setComment(reviewLocationHotelDto.getComment());
        reviewLocationHotel.setType(reviewLocationHotelDto.getType());

        User user = userRepository.findById(reviewLocationHotelDto.getUser_id())
                .orElseThrow(() ->new RuntimeException("User not found"));
        reviewLocationHotel.setUser(user);

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/Location&Hotel_Reviews-images/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }
        }
        reviewLocationHotel.setImagePaths(imagePaths);



        ReviewLocationHotel savedReviewLocationHotel = reviewLocationHotelRepository.save(reviewLocationHotel);
        reviewLocationHotelDto.setId(savedReviewLocationHotel.getId());
        return reviewLocationHotelDto;
    }

    @Override
    public List<ReviewLocationHotelDto> getAllReview(){
        return reviewLocationHotelRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewLocationHotelDto getById(Long id){
        ReviewLocationHotel reviewLocationHotel = reviewLocationHotelRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Review Not Found"));
        return convertToDto(reviewLocationHotel);
    }

    @Override
    public ReviewLocationHotelDto updateReview(Long id,ReviewLocationHotelDto reviewLocationHotelDto,MultipartFile[] images)throws IOException{
        ReviewLocationHotel reviewLocationHotel = reviewLocationHotelRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review Not Found"));
        reviewLocationHotel.setRating(reviewLocationHotelDto.getRating());
        reviewLocationHotel.setComment(reviewLocationHotelDto.getComment());
        reviewLocationHotel.setType(reviewLocationHotelDto.getType());

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/Location&Hotel_Reviews-images/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }

            reviewLocationHotel.setImagePaths(imagePaths);
        }

        ReviewLocationHotel updatedReviewLocationHote = reviewLocationHotelRepository.save(reviewLocationHotel);
        reviewLocationHotelDto.setId(updatedReviewLocationHote.getId());
        return reviewLocationHotelDto;
    }

    private ReviewLocationHotelDto convertToDto(ReviewLocationHotel reviewLocationHotel){
        ReviewLocationHotelDto dto = new ReviewLocationHotelDto();
        dto.setId(reviewLocationHotel.getId());
        dto.setRating(reviewLocationHotel.getRating());
        dto.setComment(reviewLocationHotel.getComment());
        dto.setImagePaths(reviewLocationHotel.getImagePaths());
        if (reviewLocationHotel.getUser() != null) {
            dto.setUser_id(reviewLocationHotel.getUser().getId());
        } else {
            dto.setUser_id(null);
        }
        return dto;
    }
}
