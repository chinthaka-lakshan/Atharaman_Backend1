package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.dto.GuideRequestDto;
import com.example.AtharamanBackend1.entity.Guide;
import com.example.AtharamanBackend1.entity.GuideRequest;
import com.example.AtharamanBackend1.entity.RequestStatus;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.entity.Vehicle;
import com.example.AtharamanBackend1.repository.GuideRepository;
import com.example.AtharamanBackend1.repository.GuideRequestRepository;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.service.GuideService;
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
public class GuideServiceImpl implements GuideService {

    @Autowired
    private GuideRequestRepository guideRequestRepo;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public GuideDto createGuide(GuideDto guideDto, MultipartFile[] images) throws IOException {
        Guide guide = new Guide();
        guide.setGuideName(guideDto.getGuideName());
        guide.setBusinessMail(guideDto.getBusinessMail());
        guide.setPersonalNumber(guideDto.getPersonalNumber());
        guide.setWhatsappNumber(guideDto.getWhatsappNumber());
        guide.setLanguages(guideDto.getLanguages());
        guide.setDescription(guideDto.getDescription());
        guide.setLocations(guideDto.getLocations());

        User user = userRepository.findById(guideDto.getUser_id())
                .orElseThrow(() ->new RuntimeException("User not found"));
        guide.setUser(user);

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/guide-packages/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }
        }
        guide.setImagePaths(imagePaths);



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
    public GuideDto updateGuideById(Long id,GuideDto guideDto, MultipartFile[] images) throws IOException {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Guide Not Found"));
        guide.setGuideName(guideDto.getGuideName());
        guide.setBusinessMail(guideDto.getBusinessMail());
        guide.setPersonalNumber(guideDto.getPersonalNumber());
        guide.setWhatsappNumber(guideDto.getWhatsappNumber());
        guide.setLanguages(guideDto.getLanguages());
        guide.setDescription(guideDto.getDescription());
        guide.setLocations(guideDto.getLocations());

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/guide-packages/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }

            guide.setImagePaths(imagePaths);
        }

        Guide updated = guideRepository.save(guide);
        guideDto.setId(updated.getId());
        return guideDto;
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

    @Override
    public List<GuideDto> getGuidesBylocations(List<String> locations){
        List<Guide> allGuides = guideRepository.findAll();
        return allGuides.stream()
                .filter(hotel -> hotel.getLocations() != null &&
                        hotel.getLocations().stream().anyMatch(loc ->
                                locations.stream().anyMatch(input ->
                                        loc.toLowerCase().contains(input.toLowerCase()))))
                .map(this::convertToDto)
                .collect(Collectors.toList());
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
        dto.setLocations(guide.getLocations());
        dto.setImagePaths(guide.getImagePaths());
        if (guide.getUser() != null) {
            dto.setUser_id(guide.getUser().getId());
        } else {
            dto.setUser_id(null);
        }
        return dto;
    }


    @Override
    public void submitGuideRequest(GuideRequestDto dto) {
        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        GuideRequest request = new GuideRequest();
        request.setGuideName(dto.getGuideName());
        request.setBusinessMail(dto.getBusinessMail());
        request.setPersonalNumber(dto.getPersonalNumber());
        request.setWhatsappNumber(dto.getWhatsappNumber());
        request.setLanguages(dto.getLanguages());
        request.setDescription(dto.getDescription());
        request.setUser(user);
        request.setStatus(RequestStatus.PENDING);

        guideRequestRepo.save(request);
    }





    @Override
    public void approveGuideRequest(Long requestId) {
        GuideRequest request = guideRequestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Guide request not found"));

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Request is already processed");
        }

        Guide guide = new Guide();
        guide.setGuideName(request.getGuideName());
        guide.setUser(request.getUser());
        guide.setGuideName(request.getGuideName());
        guide.setBusinessMail(request.getBusinessMail());
        guide.setPersonalNumber(request.getPersonalNumber());
        guide.setWhatsappNumber(request.getWhatsappNumber());
        guide.setLanguages(request.getLanguages());
        guide.setDescription(request.getDescription());


        guideRepository.save(guide);

        request.setStatus(RequestStatus.APPROVED);
        guideRequestRepo.save(request);


    }

    @Override
    public List<GuideRequestDto> getPendingGuideRequests() {
        List<GuideRequest> requests = guideRequestRepo.findByStatus(RequestStatus.PENDING);

        return requests.stream().map(req -> {
            GuideRequestDto dto = new GuideRequestDto();
            dto.setUser_id(req.getUser().getId());
            dto.setGuideName(req.getGuideName());
            dto.setBusinessMail(req.getBusinessMail());
            dto.setPersonalNumber(req.getPersonalNumber());
            dto.setWhatsappNumber(req.getWhatsappNumber());
            dto.setLanguages(req.getLanguages());
            dto.setDescription(req.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

}
