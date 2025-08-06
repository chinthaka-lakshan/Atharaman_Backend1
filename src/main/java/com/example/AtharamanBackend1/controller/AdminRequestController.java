package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.*;
import com.example.AtharamanBackend1.service.GuideService;
import com.example.AtharamanBackend1.service.HotelOwnerService;
import com.example.AtharamanBackend1.service.ShopOwnerService;
import com.example.AtharamanBackend1.service.VehicleOwnerService;
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

    @Autowired
    private ShopOwnerService shopOwnerService;

    @Autowired
    private VehicleOwnerService vehicleOwnerService;

    @GetMapping("guide_requests")
    public ResponseEntity<List<GuideRequestDto>> getAllPendingRequests() {
        return ResponseEntity.ok(guideService.getPendingGuideRequests());
    }

    @PostMapping("/guide_approve/{id}")
    public ResponseEntity<String> approveRequest(@PathVariable Long id) {
        guideService.approveGuideRequest(id);
        return ResponseEntity.ok("Guide request approved successfully.");
    }


    @GetMapping("hotel_owner_requests")
    public ResponseEntity<List<HotelOwnerRequestDto>> getAllPendingHotelOwnerRequests() {
        return ResponseEntity.ok(hotelOwnerService.getPendingHotelOwnerRequests());
    }

    @PostMapping("/hotel_owner_approve/{id}")
    public ResponseEntity<String> approveHotelOwnerRequest(@PathVariable Long id) {
        hotelOwnerService.approveHotelOwnerRequest(id);
        return ResponseEntity.ok("Hotel Owner request approved successfully.");
    }




    @GetMapping("shopowner_requests")
    public ResponseEntity<List<ShopOwnerRequestDto>> getAllPendingShopOwnerRequests() {
        return ResponseEntity.ok(shopOwnerService.getPendingShopOwnerRequests());
    }

    @PostMapping("/shopowner_approve/{id}")
    public ResponseEntity<String> approveShopOwnerRequest(@PathVariable Long id) {
        shopOwnerService.approveShopOwnerRequest(id);
        return ResponseEntity.ok("Shop Owner request approved successfully.");
    }



    @GetMapping("vehicle_owner_requests")
    public ResponseEntity<List<VehicleOwnerRequestDto>> getAllPendingVehicleOwnerRequests() {
        return ResponseEntity.ok(vehicleOwnerService.getPendingVehicleOwnerRequests());
    }

    @PostMapping("/vehicle_owner_approve/{id}")
    public ResponseEntity<String> approveVehicleOwnerRequest(@PathVariable Long id) {
        vehicleOwnerService.approveVehicleOwnerRequest(id);
        return ResponseEntity.ok("Vehicle Owner request approved successfully.");
    }


}

