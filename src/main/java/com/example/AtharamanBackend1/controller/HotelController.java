package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.dto.HotelDto;
import com.example.AtharamanBackend1.entity.Hotel;
import com.example.AtharamanBackend1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping(consumes = "multipart/form-data")
    public HotelDto addHotel(
            @RequestPart("hotel") HotelDto hotelDto,
            @RequestPart("images")MultipartFile[] images) throws IOException {
        return hotelService.createHotel(hotelDto,images);
    }

    @GetMapping
    public List<Object> getAllHotels() {return hotelService.getAllHotels();}

    @GetMapping("/{id}")
    public HotelDto getHotelById(@PathVariable Long id){return hotelService.getHotelById(id);}

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public HotelDto updateHotel(
            @PathVariable Long id,
            @RequestPart("hotel") HotelDto hotelDto,
            @RequestPart(value = "images", required = false)MultipartFile[] images)throws IOException{
        return hotelService.updateHotelById(id, hotelDto, images);
    }
    @PostMapping("/filter")
    public List<HotelDto> filterHotels(@RequestBody HotelDto hotelDto) {
        return hotelService.getHotelByLocation(hotelDto.getLocations());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id){
        hotelService.deleteHotelById(id);
        return ResponseEntity.ok("Success");
    }

}
