package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.ShopDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ShopService {
    ShopDto createShop(ShopDto shopDto,MultipartFile[] images) throws IOException;
    List<ShopDto> getAllShops();
    ShopDto getShopById(Long id);
    List<ShopDto> geyShopsByLocation(List<String> locations);
    ShopDto updateShopById(Long id, ShopDto shopDto,MultipartFile[] images) throws IOException;
    void deleteShopById(Long id);
}
