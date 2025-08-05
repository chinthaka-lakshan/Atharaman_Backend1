package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.GuideRequestDto;
import com.example.AtharamanBackend1.dto.HotelOwnerDto;
import com.example.AtharamanBackend1.dto.HotelOwnerRequestDto;

import java.util.List;

public interface HotelOwnerService {
    HotelOwnerDto createHotelOwner(HotelOwnerDto hotelOwnerDto);
    List<HotelOwnerDto> getAllHotelOwners();
    HotelOwnerDto getHotelOwnerById(Long id);
    HotelOwnerDto updateHotelOwnerById(HotelOwnerDto hotelOwnerDto, Long id);
    void deleteHotelOwnerById(Long id);
    void submitHotelOwnerRequest(HotelOwnerRequestDto dto);
    void approveHotelOwnerRequest(Long requestId);
    List<HotelOwnerRequestDto> getPendingHotelOwnerRequests();

}
