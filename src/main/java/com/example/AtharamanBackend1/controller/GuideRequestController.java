package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideRequestDto;
import com.example.AtharamanBackend1.entity.GuideRequest;
import com.example.AtharamanBackend1.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/guides")
public class GuideRequestController {

    @Autowired
    private GuideService guideService;

    @GetMapping("/requests")
    public ResponseEntity<List<GuideRequestDto>> getAllPendingRequests() {
        return ResponseEntity.ok(guideService.getPendingGuideRequests());
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable Long id) {
        guideService.approveGuideRequest(id);
        return ResponseEntity.ok("Guide request approved successfully.");
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitGuideRequest(@RequestBody GuideRequestDto dto) {
        guideService.submitGuideRequest(dto);
        return ResponseEntity.ok("Guide request submitted successfully.");
    }
}

