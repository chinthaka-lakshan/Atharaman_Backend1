package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.entity.Guide;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.repository.GuideRepository;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public GuideDto createGuide(GuideDto guideDto) {
        Guide guide = new Guide();
        guide.setGuideName(guideDto.getGuideName());
        guide.setBusinessMail(guideDto.getBusinessMail());
        guide.setPersonalNumber(guideDto.getPersonalNumber());
        guide.setWhatsappNumber(guideDto.getWhatsappNumber());
        guide.setLanguages(guideDto.getLanguages());
        guide.setDescription(guideDto.getDescription());

        User user = userRepository.findById(guideDto.getUser_id()).orElseThrow(() ->new RuntimeException("User not found"));
        guide.setUser(user);

        Guide savedGuide = guideRepository.save(guide);
        guideDto.setId(savedGuide.getId());
        return guideDto;
    }

    @Override
    public List<GuideDto> getAllGuides() {
        return guideRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public GuideDto getGuideById(Long id){
        Guide guide = guideRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Guide Not Found"));
        return convertToDto(guide);
    }

    @Override
    public GuideDto updateGuideById(Long id,GuideDto guideDto) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Guide Not Found"));
        guide.setGuideName(guideDto.getGuideName());
        guide.setBusinessMail(guideDto.getBusinessMail());
        guide.setPersonalNumber(guideDto.getPersonalNumber());
        guide.setWhatsappNumber(guideDto.getWhatsappNumber());
        guide.setLanguages(guideDto.getLanguages());
        guide.setDescription(guideDto.getDescription());

        guideRepository.save(guide);
        return convertToDto(guide);
    }

    @Override
    public void deleteGuideById(Long id){
        Guide guide = guideRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Guide Not Found"));
        if (guide.getUser() != null) {
            User user = guide.getUser();
            user.setGuide(null);
            guide.setUser(null);
        }
        guideRepository.delete(guide);
    }

    private GuideDto convertToDto(Guide guide) {
        GuideDto dto = new GuideDto();
        dto.setId(guide.getId());
        dto.setGuideName(guide.getGuideName());
        dto.setBusinessMail(guide.getBusinessMail());
        dto.setPersonalNumber(guide.getPersonalNumber());
        dto.setWhatsappNumber(guide.getWhatsappNumber());
        dto.setLanguages(guide.getLanguages());
        dto.setDescription(guide.getDescription());
        if (guide.getUser() != null) {
            dto.setUser_id(guide.getUser().getId());
        } else {
            dto.setUser_id(null);
        }
        return dto;
    }
}
