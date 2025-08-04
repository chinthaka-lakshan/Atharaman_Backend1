package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.HotelDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto, MultipartFile[] images)throws IOException;

    List<Object> getAllHotels();

    HotelDto getHotelById(Long id);
    List<HotelDto> getHotelByLocation(List<String> locations);

    HotelDto updateHotelById(Long id, HotelDto hotelDto, MultipartFile[] images)throws IOException;

    void deleteHotelById(Long id);
}
