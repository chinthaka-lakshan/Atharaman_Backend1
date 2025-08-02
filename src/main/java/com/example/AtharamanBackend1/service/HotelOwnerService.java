package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.HotelOwnerDto;

import java.util.List;

public interface HotelOwnerService {
    HotelOwnerDto createHotelOwner(HotelOwnerDto hotelOwnerDto);
    List<HotelOwnerDto> getAllHotelOwners();
    HotelOwnerDto getHotelOwnerById(Long id);
    HotelOwnerDto updateHotelOwnerById(HotelOwnerDto hotelOwnerDto, Long id);
    void deleteHotelOwnerById(Long id);
}
