package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.dto.HotelDto;
import com.example.AtharamanBackend1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public HotelDto addHotel(@RequestBody HotelDto hotelDto ) {return hotelService.createHotel(hotelDto);}

    @GetMapping
    public List<Object> getAllHotels() {return hotelService.getAllHotels();}

    @GetMapping("/{id}")
    public HotelDto getHotelById(@PathVariable Long id){return hotelService.getHotelById(id);}

    @PutMapping("/{id}")
    public HotelDto updateHotel(@RequestBody HotelDto hotelDto, @PathVariable Long id){
        return hotelService.updateHotelById(id, hotelDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id){
        hotelService.deleteHotelById(id);
        return ResponseEntity.ok("Success");
    }

}
