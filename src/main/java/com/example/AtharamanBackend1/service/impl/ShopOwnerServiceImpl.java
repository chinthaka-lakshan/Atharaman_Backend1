package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.HotelOwnerRequestDto;
import com.example.AtharamanBackend1.dto.ShopOwnerDto;
import com.example.AtharamanBackend1.dto.ShopOwnerRequestDto;
import com.example.AtharamanBackend1.entity.*;
import com.example.AtharamanBackend1.repository.ShopOwnerRepository;
import com.example.AtharamanBackend1.repository.ShopOwnerRequestRepository;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.service.ShopOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopOwnerServiceImpl implements ShopOwnerService {

    @Autowired
    private ShopOwnerRepository shopOwnerRepository;
    @Autowired
    private ShopOwnerRequestRepository shopOwnerRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ShopOwnerDto createShopOwner(ShopOwnerDto shopOwnerDto) {
        ShopOwner shopOwner = new ShopOwner();
        shopOwner.setShopOwnerName(shopOwnerDto.getShopOwnerName());
        shopOwner.setShopOwnerNic(shopOwnerDto.getShopOwnerNic());
        shopOwner.setBusinessMail(shopOwnerDto.getBusinessMail());
        shopOwner.setContactNumber(shopOwnerDto.getContactNumber());


        User user = userRepository.findById(shopOwnerDto.getUser_id()).orElseThrow(() -> new RuntimeException("User not found"));
        shopOwner.setUser(user);

        ShopOwner savedShopOwner = shopOwnerRepository.save(shopOwner);
        shopOwnerDto.setId(savedShopOwner.getId());
        return shopOwnerDto;
    }

    @Override
    public List<ShopOwnerDto> getAllShopOwners() {
        return shopOwnerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShopOwnerDto getShopOwnerById(Long id) {
        ShopOwner shopOwner = shopOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShopOwner Not Found"));
        return convertToDto(shopOwner);
    }

    @Override
    public ShopOwnerDto updateShopOwnerById(Long id, ShopOwnerDto shopOwnerDto) {
        ShopOwner shopOwner = shopOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShopOwner Not Found"));
        shopOwner.setShopOwnerName(shopOwnerDto.getShopOwnerName());
        shopOwner.setShopOwnerNic(shopOwnerDto.getShopOwnerNic());
        shopOwner.setBusinessMail(shopOwnerDto.getBusinessMail());
        shopOwner.setContactNumber(shopOwnerDto.getContactNumber());


        shopOwnerRepository.save(shopOwner);
        return convertToDto(shopOwner);
    }

    @Override
    public void deleteShopOwnerById(Long id) {
        ShopOwner shopOwner = shopOwnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShopOwner Not Found"));
        if (shopOwner.getUser() != null) {
            User user = shopOwner.getUser();
            user.setShopOwner(null);
            shopOwner.setUser(null);
        }
        shopOwnerRepository.delete(shopOwner);
    }


    private ShopOwnerDto convertToDto(ShopOwner shopOwner) {
        ShopOwnerDto dto = new ShopOwnerDto();
        dto.setId(shopOwner.getId());
        dto.setShopOwnerName(shopOwner.getShopOwnerName());
        dto.setShopOwnerNic(shopOwner.getShopOwnerNic());
        dto.setBusinessMail(shopOwner.getBusinessMail());
        dto.setContactNumber(shopOwner.getContactNumber());


        if (shopOwner.getUser() != null) {
            dto.setUser_id(shopOwner.getUser().getId());
        } else {
            dto.setUser_id(null);
        }
        return dto;
    }


    @Override
    public void submitShopOwnerRequest(ShopOwnerRequestDto dto) {
        User user = userRepository.findById(dto.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ShopOwnerRequest request = new ShopOwnerRequest();
        request.setId(dto.getId());
        request.setShopOwnerName(dto.getShopOwnerName());
        request.setShopOwnerNic(dto.getShopOwnerNic());
        request.setBusinessMail(dto.getBusinessMail());
        request.setContactNumber(dto.getContactNumber());

        request.setUser(user);
        request.setStatus(RequestStatus.PENDING);

        shopOwnerRequestRepository.save(request);
    }





    @Override
    public void approveShopOwnerRequest(Long requestId) {
        ShopOwnerRequest request = shopOwnerRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Hotel Owner request not found"));

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Request is already processed");
        }

        ShopOwner shopOwner = new ShopOwner();
        shopOwner.setShopOwnerName(request.getShopOwnerName());
        shopOwner.setUser(request.getUser());
        shopOwner.setShopOwnerNic(request.getShopOwnerNic());
        shopOwner.setBusinessMail(request.getBusinessMail());
        shopOwner.setContactNumber(request.getContactNumber());

        shopOwnerRepository.save(shopOwner);

        request.setStatus(RequestStatus.APPROVED);
        shopOwnerRequestRepository.save(request);


    }

    @Override
    public List<ShopOwnerRequestDto> getPendingShopOwnerRequests() {
        List<ShopOwnerRequest> requests = shopOwnerRequestRepository.findByStatus(RequestStatus.PENDING);

        return requests.stream().map(req -> {
            ShopOwnerRequestDto dto = new ShopOwnerRequestDto();
            dto.setUser_id(req.getUser().getId());
            dto.setId(req.getId());
            dto.setShopOwnerName(req.getShopOwnerName());
            dto.setShopOwnerNic(req.getShopOwnerNic());
            dto.setBusinessMail(req.getBusinessMail());
            dto.setContactNumber(req.getContactNumber());

            return dto;
        }).collect(Collectors.toList());
    }

}




