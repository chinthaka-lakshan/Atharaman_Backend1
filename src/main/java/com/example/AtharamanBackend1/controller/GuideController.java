package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.dto.GuideRequestDto;
import com.example.AtharamanBackend1.dto.VehicleDto;
import com.example.AtharamanBackend1.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/guides")
public class GuideController {
    @Autowired
    private GuideService guideService;



    @GetMapping
    public List<GuideDto> getAllGuides() {
        return guideService.getAllGuides();
    }

    @GetMapping("/{id}")
    public GuideDto getGuide(@PathVariable Long id){
        return guideService.getGuideById(id);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public GuideDto updateGuide(
            @PathVariable Long id,
            @RequestPart ("guide") GuideDto guideDto,
            @RequestPart(value = "images", required = false) MultipartFile[] images)throws IOException{
        return guideService.updateGuideById(id, guideDto, images);
    }

    @PostMapping("/filter")
    public List<GuideDto> getGuidesBylocations(@RequestBody GuideDto guideDto){
        return guideService.getGuidesBylocations(guideDto.getLocations());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuide(@PathVariable Long id){
        guideService.deleteGuideById(id);
        return ResponseEntity.ok("Guide Deleted Success");
    }

    @PostMapping("/request")
    public ResponseEntity<String> submitGuideRequest(@RequestBody GuideRequestDto dto) {
        guideService.submitGuideRequest(dto);
        return ResponseEntity.ok("Guide request submitted successfully.");
    }
}
