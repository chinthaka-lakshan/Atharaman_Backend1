package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.HotelDto;
import com.example.AtharamanBackend1.entity.Hotel;
import com.example.AtharamanBackend1.entity.HotelOwner;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.repository.HotelOwnerRepository;
import com.example.AtharamanBackend1.repository.HotelRepository;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelOwnerRepository hotelOwnerRepository;

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelDto.getHotelName());
        hotel.setBusinessMail(hotelDto.getBusinessMail());
        hotel.setContactNumber(hotelDto.getContactNumber());
        hotel.setWhatsappNumber(hotelDto.getWhatsappNumber());
        hotel.setNicNumber(hotelDto.getNicNumber());
        hotel.setLocations(hotelDto.getLocations());

        User user = userRepository.findById(hotelDto.getUser_id()).orElseThrow(() ->new RuntimeException("User not found"));
        hotel.setUser(user);

        HotelOwner hotelOwner=hotelOwnerRepository.findById(hotelDto.getHotelOwner_id()).orElseThrow(() ->new RuntimeException("HotelOwner not found"));
        hotel.setHotelOwner(hotelOwner);

        Hotel savedHotel = hotelRepository.save(hotel);
        hotelDto.setId(savedHotel.getId());
        return hotelDto;
    }

    @Override
    public List<Object> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    @Override
    public HotelDto getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new RuntimeException("Hotel not found"));
        return convertToDto(hotel);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new RuntimeException("Hotel not found"));
        hotel.setHotelName(hotelDto.getHotelName());
        hotel.setBusinessMail(hotelDto.getBusinessMail());
        hotel.setContactNumber(hotelDto.getContactNumber());
        hotel.setWhatsappNumber(hotelDto.getWhatsappNumber());
        hotel.setNicNumber(hotelDto.getNicNumber());
        hotel.setLocations(hotelDto.getLocations());

        hotelRepository.save(hotel);
        return convertToDto(hotel);

    }

    @Override
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new RuntimeException("Hotel not found"));
        if (hotel.getUser() != null) {
            User user = hotel.getUser();
            user.setHotels(null);
            hotel.setUser(null);
        }

        if (hotel.getHotelOwner() != null) {
            HotelOwner hotelOwner = hotel.getHotelOwner();
            hotelOwner.setHotels(null);
            hotel.setHotelOwner(null);
        }
        hotelRepository.delete(hotel);
    }



    private HotelDto convertToDto(Hotel hotel) {
        HotelDto dto = new HotelDto();
        dto.setId(hotel.getId());
        dto.setHotelName(hotel.getHotelName());
        dto.setBusinessMail(hotel.getBusinessMail());
        dto.setContactNumber(hotel.getContactNumber());
        dto.setWhatsappNumber(hotel.getWhatsappNumber());
        dto.setNicNumber(hotel.getNicNumber());
        dto.setLocations(hotel.getLocations());
        if (hotel.getUser() != null) {
            dto.setUser_id(hotel.getUser().getId());
        } else {
            dto.setUser_id(null);
        }

        if (hotel.getHotelOwner() != null) {
            dto.setHotelOwner_id(hotel.getHotelOwner().getId());
        } else {
            dto.setHotelOwner_id(null);
        }
        return dto;
    }

}
