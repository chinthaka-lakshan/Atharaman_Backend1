package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.HotelOwnerDto;
import com.example.AtharamanBackend1.entity.HotelOwner;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.repository.HotelOwnerRepository;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.service.HotelOwnerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelOwnerServiceImpl implements HotelOwnerService {
    @Autowired
    private HotelOwnerRepository hotelOwnerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public HotelOwnerDto createHotelOwner(HotelOwnerDto hotelOwnerDto) {
        HotelOwner hotelOwner = new HotelOwner();

        hotelOwner.setOwnerName(hotelOwnerDto.getHotelOwnerName());
        hotelOwner.setHotelOwnerNic(hotelOwnerDto.getHotelOwnerNic());
        hotelOwner.setBusinessMail(hotelOwnerDto.getBusinessMail());
        hotelOwner.setContactNumber(hotelOwnerDto.getContactNumber());

        User user = userRepository.findById(hotelOwnerDto.getUser_id())
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        hotelOwner.setUser(user);

        HotelOwner savedHotelOwner = hotelOwnerRepository.save(hotelOwner);
        hotelOwnerDto.setId(savedHotelOwner.getId());
        return hotelOwnerDto;
    }

    @Override
    public List<HotelOwnerDto> getAllHotelOwners() {
        return hotelOwnerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelOwnerDto getHotelOwnerById(Long id){
        HotelOwner hotelOwner = hotelOwnerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("HotelOwner not found"));
        return convertToDto(hotelOwner);
    }

    @Override
    public HotelOwnerDto updateHotelOwnerById(HotelOwnerDto hotelOwnerDto, Long id) {
        HotelOwner hotelOwner = hotelOwnerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("HotelOwner not found"));
        hotelOwner.setOwnerName(hotelOwnerDto.getHotelOwnerName());
        hotelOwner.setHotelOwnerNic(hotelOwnerDto.getHotelOwnerNic());
        hotelOwner.setBusinessMail(hotelOwnerDto.getBusinessMail());
        hotelOwner.setContactNumber(hotelOwnerDto.getContactNumber());

        hotelOwnerRepository.save(hotelOwner);
        return convertToDto(hotelOwner);
    }
    @Override
    public void deleteHotelOwnerById(Long id) {
        HotelOwner hotelOwner = hotelOwnerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("HotelOwner not found"));
        if (hotelOwner.getUser()!=null) {
            User user = hotelOwner.getUser();
            user.setHotelOwner(null);
            hotelOwner.setUser(null);
        }
        hotelOwnerRepository.delete(hotelOwner);
    }

    private HotelOwnerDto convertToDto (HotelOwner hotelOwner) {
        HotelOwnerDto hotelOwnerDto = new HotelOwnerDto();
        hotelOwnerDto.setId(hotelOwner.getId());
        hotelOwnerDto.setHotelOwnerName(hotelOwner.getOwnerName());
        hotelOwnerDto.setHotelOwnerNic(hotelOwner.getHotelOwnerNic());
        hotelOwnerDto.setBusinessMail(hotelOwner.getBusinessMail());
        hotelOwnerDto.setContactNumber(hotelOwner.getContactNumber());

        if(hotelOwner.getUser()!=null){
            hotelOwnerDto.setUser_id(hotelOwner.getUser().getId());
        }else {
            hotelOwnerDto.setUser_id(null);
        }
        return hotelOwnerDto;
    }


}
