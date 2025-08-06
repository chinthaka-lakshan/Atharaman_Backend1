package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideRequestDto;
import com.example.AtharamanBackend1.dto.HotelOwnerDto;
import com.example.AtharamanBackend1.dto.HotelOwnerRequestDto;
import com.example.AtharamanBackend1.repository.HotelOwnerRepository;
import com.example.AtharamanBackend1.service.HotelOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotelOwners")
public class HotelOwnerController {
    @Autowired
    private HotelOwnerService hotelOwnerService;



    @GetMapping
    public List<HotelOwnerDto> getAllHotelOwners(){
        return hotelOwnerService.getAllHotelOwners();
    }

    @GetMapping("/{id}")
    public HotelOwnerDto getHotelOwnerById(@PathVariable Long id){
        return hotelOwnerService.getHotelOwnerById(id);
    }

    @PutMapping("/{id}")
    public HotelOwnerDto updateHotelOwner(@PathVariable Long id, @RequestBody HotelOwnerDto hotelOwnerDto){
        return hotelOwnerService.updateHotelOwnerById(hotelOwnerDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotelOwnerById(@PathVariable Long id){
        hotelOwnerService.deleteHotelOwnerById(id);
        return ResponseEntity.ok("Hotel Owner has been deleted");
    }



    @PostMapping("/request")
    public ResponseEntity<String> submitShopOwnerRequest(@RequestBody HotelOwnerRequestDto dto) {
        hotelOwnerService.submitHotelOwnerRequest(dto);
        return ResponseEntity.ok("Hotel Owner request submitted successfully.");
    }
}
