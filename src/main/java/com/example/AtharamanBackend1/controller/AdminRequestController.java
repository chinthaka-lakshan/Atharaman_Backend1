package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideRequestDto;
import com.example.AtharamanBackend1.dto.HotelOwnerDto;
import com.example.AtharamanBackend1.dto.HotelOwnerRequestDto;
import com.example.AtharamanBackend1.service.GuideService;
import com.example.AtharamanBackend1.service.HotelOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/requests")
public class AdminRequestController {

    @Autowired
    private GuideService guideService;

    @Autowired
    private HotelOwnerService hotelOwnerService;

    @GetMapping("guide requests")
    public ResponseEntity<List<GuideRequestDto>> getAllPendingRequests() {
        return ResponseEntity.ok(guideService.getPendingGuideRequests());
    }

    @PostMapping("/guide approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable Long id) {
        guideService.approveGuideRequest(id);
        return ResponseEntity.ok("Guide request approved successfully.");
    }


    @GetMapping("shopowner requests")
    public ResponseEntity<List<HotelOwnerRequestDto>> getAllPendingShopOwnerRequests() {
        return ResponseEntity.ok(hotelOwnerService.getPendingHotelOwnerRequests());
    }

    @PostMapping("/shopowner approve/{id}")
    public ResponseEntity<String> approveShopOwnerRequest(@PathVariable Long id) {
        hotelOwnerService.approveHotelOwnerRequest(id);
        return ResponseEntity.ok("Hotel Owner request approved successfully.");
    }


}

