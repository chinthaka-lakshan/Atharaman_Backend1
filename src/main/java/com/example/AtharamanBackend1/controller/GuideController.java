package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guides")
public class GuideController {
    @Autowired
    private GuideService guideService;

    @PostMapping
    public GuideDto addGuide(@RequestBody GuideDto guideDto) {
        return guideService.createGuide(guideDto);
    }
    @GetMapping
    public List<GuideDto> getAllGuides() {
        return guideService.getAllGuides();
    }
    @GetMapping("/{id}")
    public GuideDto getGuide(@PathVariable Long id){
        return guideService.getGuideById(id);
    }
}
