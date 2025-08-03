package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);

    List<Object> getAllHotels();

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);
}
