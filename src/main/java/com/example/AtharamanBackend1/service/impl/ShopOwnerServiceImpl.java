package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.ShopOwnerDto;
import com.example.AtharamanBackend1.entity.ShopOwner;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.repository.ShopOwnerRepository;
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
}