package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.GuideDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GuideService {
    GuideDto createGuide(GuideDto guideDto, MultipartFile[] images) throws IOException;
    List<GuideDto> getAllGuides();
    GuideDto getGuideById(Long id);
    GuideDto updateGuideById(Long id, GuideDto guideDto);
    void deleteGuideById(Long id);
}
