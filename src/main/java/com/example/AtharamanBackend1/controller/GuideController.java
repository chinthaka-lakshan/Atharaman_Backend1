package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideDto;
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

    @PostMapping(consumes = "multipart/form-data")
    public GuideDto createGuide(
            @RequestPart("guide") GuideDto guideDto,
            @RequestPart("images") MultipartFile[] images) throws IOException {
        return guideService.createGuide(guideDto, images);
    }

    @GetMapping
    public List<GuideDto> getAllGuides() {
        return guideService.getAllGuides();
    }
    @GetMapping("/{id}")
    public GuideDto getGuide(@PathVariable Long id){
        return guideService.getGuideById(id);
    }

    @PutMapping("/{id}")
    public GuideDto updateGuide(@RequestBody GuideDto guideDto, @PathVariable Long id){
        return guideService.updateGuideById(id, guideDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuide(@PathVariable Long id){
        guideService.deleteGuideById(id);
        return ResponseEntity.ok("Success");
    }
}
