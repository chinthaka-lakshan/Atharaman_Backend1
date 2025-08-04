package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.dto.GuideRequestDto;

import org.springframework.web.multipart.MultipartFile;

import com.example.AtharamanBackend1.entity.GuideRequest;

import java.io.IOException;
import java.util.List;

public interface GuideService {
    GuideDto createGuide(GuideDto guideDto, MultipartFile[] images)throws IOException;
    List<GuideDto> getAllGuides();
    GuideDto getGuideById(Long id);
    GuideDto updateGuideById(Long id, GuideDto guideDto,MultipartFile[] images)throws IOException;
    void deleteGuideById(Long id);
    void submitGuideRequest(GuideRequestDto dto);
    void approveGuideRequest(Long requestId);
    List<GuideRequestDto> getPendingGuideRequests();
    List<GuideDto> getGuidesBylocations(List<String> locations);



}
