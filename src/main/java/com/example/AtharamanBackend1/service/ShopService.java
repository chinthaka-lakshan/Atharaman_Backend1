package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.ShopDto;

import java.util.List;

public interface ShopService {
    ShopDto createShop(ShopDto shopDto);
    List<ShopDto> getAllShops();
    ShopDto getShopById(Long id);
    ShopDto updateShopById(Long id, ShopDto shopDto);
    List<ShopDto> geyShopsByLocation(List<String> locations);
    void deleteShopById(Long id);
}
