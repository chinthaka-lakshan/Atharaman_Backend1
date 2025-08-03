package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.ShopOwnerDto;

import java.util.List;

public interface ShopOwnerService {
    ShopOwnerDto createShopOwner(ShopOwnerDto shopOwnerDto);
    List<ShopOwnerDto> getAllShopOwners();
    ShopOwnerDto getShopOwnerById(Long id);
    ShopOwnerDto updateShopOwnerById(Long id, ShopOwnerDto shopOwnerDto);
    void deleteShopOwnerById(Long id);
}
