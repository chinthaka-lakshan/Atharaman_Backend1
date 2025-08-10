package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.OtherReviewDto;
import com.example.AtharamanBackend1.service.OtherReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/otherReview")
public class OtherReviewController {

    @Autowired
    private OtherReviewService otherReviewService;

    @PostMapping
    public OtherReviewDto createReview(OtherReviewDto otherReviewDto){
        return otherReviewService.createReview(otherReviewDto);
    }

    @GetMapping
    public List<OtherReviewDto> getAllReview(){
        return otherReviewService.getAllReview();
    }

    @GetMapping("/{id}")
    public OtherReviewDto getById(@PathVariable Long id){
        return otherReviewService.getById(id);
    }

    @PutMapping("/{id}")
    public OtherReviewDto updateReview(@RequestBody OtherReviewDto otherReviewDto, @PathVariable Long id){
        return otherReviewService.updateReview(id,otherReviewDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        otherReviewService.deleteReview(id);
    }
}
