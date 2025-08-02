package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.GuideDto;

import java.util.List;

public interface GuideService {
    GuideDto createGuide(GuideDto guideDto);
    List<GuideDto> getAllGuides();
    GuideDto getGuideById(Long id);
//    GuideDto updateGuideById(Long id, GuideDto guideDto);


}
