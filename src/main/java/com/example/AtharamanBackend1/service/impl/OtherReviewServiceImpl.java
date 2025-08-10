package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.OtherReviewDto;
import com.example.AtharamanBackend1.entity.Guide;
import com.example.AtharamanBackend1.entity.Hotel;
import com.example.AtharamanBackend1.entity.OtherReview;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.repository.OtherReviewRepository;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.service.OtherReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OtherReviewServiceImpl implements OtherReviewService {

    @Autowired
    private OtherReviewRepository otherReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OtherReviewDto createReview(OtherReviewDto otherReviewDto){
        OtherReview otherReview = new OtherReview();
        otherReview.setId(otherReviewDto.getId());
        otherReview.setRating(otherReviewDto.getRating());
        otherReview.setComment(otherReviewDto.getComment());
        otherReview.setType(otherReviewDto.getType());

        User user = userRepository.findById(otherReviewDto.getUser_id())
                .orElseThrow(() ->new RuntimeException("User not found"));
        otherReview.setUser(user);

        OtherReview savedOtherReview = otherReviewRepository.save(otherReview);
        otherReviewDto.setId(savedOtherReview.getId());
        return otherReviewDto;
    }

    @Override
    public List<OtherReviewDto> getAllReview(){
        return otherReviewRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OtherReviewDto getById(Long id){
        OtherReview otherReview = otherReviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review Not Found"));
        return convertToDto(otherReview);
    }

    @Override
    public OtherReviewDto updateReview(Long id, OtherReviewDto otherReviewDto){
        OtherReview otherReview = otherReviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review Not Found"));
        otherReview.setRating(otherReviewDto.getRating());
        otherReview.setComment(otherReviewDto.getComment());

        otherReviewRepository.save(otherReview);
        return  convertToDto(otherReview);
    }

    @Override
    public void deleteReview(Long id){
        OtherReview otherReview = otherReviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review Not Found"));
        if(otherReview.getUser()!=null){
            User user = otherReview.getUser();
            user.setOtherReviews(null);
            otherReview.setUser(null);
        }
        otherReviewRepository.delete(otherReview);
    }

    private OtherReviewDto convertToDto(OtherReview otherReview){
        OtherReviewDto dto = new OtherReviewDto();
        dto.setId(otherReview.getId());
        dto.setRating(otherReview.getRating());
        dto.setComment(otherReview.getComment());
        dto.setType(otherReview.getType());
        if (otherReview.getUser() != null) {
            dto.setUser_id(otherReview.getUser().getId());
        } else {
            dto.setUser_id(null);
        }
        return dto;
    }
}
