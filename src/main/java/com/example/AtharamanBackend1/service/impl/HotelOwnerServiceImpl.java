package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.GuideRequestDto;
import com.example.AtharamanBackend1.dto.HotelOwnerDto;
import com.example.AtharamanBackend1.dto.HotelOwnerRequestDto;
import com.example.AtharamanBackend1.entity.*;
import com.example.AtharamanBackend1.repository.HotelOwnerRepository;
import com.example.AtharamanBackend1.repository.HotelOwnerRequestRepository;
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
    @Autowired
    private HotelOwnerRequestRepository hotelOwnerRequestRepository;

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





    @Override
    public void submitHotelOwnerRequest(HotelOwnerRequestDto dto) {
        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        HotelOwnerRequest request = new HotelOwnerRequest();
        request.setId(dto.getId());
        request.setOwnerName(dto.getOwnerName());
        request.setHotelOwnerNic(dto.getHotelOwnerNic());
        request.setBusinessMail(dto.getBusinessMail());
        request.setContactNumber(dto.getContactNumber());
        request.setDescription(dto.getDescription());
        request.setUser(user);
        request.setStatus(RequestStatus.PENDING);

        hotelOwnerRequestRepository.save(request);
    }





    @Override
    public void approveHotelOwnerRequest(Long requestId) {
        HotelOwnerRequest request = hotelOwnerRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Hotel Owner request not found"));

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Request is already processed");
        }

        HotelOwner hotelOwner = new HotelOwner();
        hotelOwner.setOwnerName(request.getOwnerName());
        hotelOwner.setUser(request.getUser());
        hotelOwner.setHotelOwnerNic(request.getHotelOwnerNic());
        hotelOwner.setBusinessMail(request.getBusinessMail());
        hotelOwner.setContactNumber(request.getContactNumber());
        hotelOwner.setDescription(request.getDescription());



        hotelOwnerRepository.save(hotelOwner);

        request.setStatus(RequestStatus.APPROVED);
        hotelOwnerRequestRepository.save(request);


    }

    @Override
    public List<HotelOwnerRequestDto> getPendingHotelOwnerRequests() {
        List<HotelOwnerRequest> requests = hotelOwnerRequestRepository.findByStatus(RequestStatus.PENDING);

        return requests.stream().map(req -> {
            HotelOwnerRequestDto dto = new HotelOwnerRequestDto();
            dto.setUser_id(req.getUser().getId());
            dto.setId(req.getId());
            dto.setOwnerName(req.getOwnerName());
            dto.setHotelOwnerNic(req.getHotelOwnerNic());
            dto.setBusinessMail(req.getBusinessMail());
            dto.setHotelOwnerNic(req.getHotelOwnerNic());
            dto.setContactNumber(req.getContactNumber());
            dto.setDescription(req.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

}



