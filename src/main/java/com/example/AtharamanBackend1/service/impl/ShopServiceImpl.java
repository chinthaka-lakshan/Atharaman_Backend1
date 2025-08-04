package com.example.AtharamanBackend1.service.impl;

import com.example.AtharamanBackend1.dto.ShopDto;
import com.example.AtharamanBackend1.entity.Shop;
import com.example.AtharamanBackend1.entity.ShopOwner;
import com.example.AtharamanBackend1.entity.User;
import com.example.AtharamanBackend1.repository.ShopOwnerRepository;
import com.example.AtharamanBackend1.repository.ShopRepository;
import com.example.AtharamanBackend1.repository.UserRepository;
import com.example.AtharamanBackend1.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopOwnerRepository shopOwnerRepository;

    @Override
    public ShopDto createShop(ShopDto shopDto, MultipartFile[] images)throws IOException {
        Shop shop = new Shop();
        shop.setShopName(shopDto.getShopName());
        shop.setLocations(shopDto.getLocations());
        shop.setDescription(shopDto.getDescription());

        User user = userRepository.findById(shopDto.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        shop.setUser(user);

        ShopOwner shopOwner = shopOwnerRepository.findById(shopDto.getShopOwner_id())
                .orElseThrow(()->new RuntimeException("Shop Owner Not Found"));
        shop.setShopOwner(shopOwner);

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/shop-packages/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }
        }
        shop.setImagePaths(imagePaths);


        Shop savedShop = shopRepository.save(shop);
        shopDto.setId(savedShop.getId());
        return shopDto;
    }

    @Override
    public List<ShopDto> getAllShops() {
        return shopRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto getShopById(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop Not Found"));
        return convertToDto(shop);
    }

    @Override
    public ShopDto updateShopById(Long id, ShopDto shopDto, MultipartFile[] images) throws IOException {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop Not Found"));
        shop.setShopName(shopDto.getShopName());
        shop.setLocations(shopDto.getLocations());
        shop.setDescription(shopDto.getDescription());

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/shop-packages/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }
        }
        shop.setImagePaths(imagePaths);


        Shop updated = shopRepository.save(shop);
        shopDto.setId(updated.getId());
        return shopDto;

    }

    @Override
    public void deleteShopById(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop Not Found"));
        if (shop.getUser() != null) {
            User user = shop.getUser();
            user.setShops(null);
            shop.setUser(null);
        }
        shopRepository.delete(shop);
    }


    private ShopDto convertToDto(Shop shop) {
        ShopDto dto = new ShopDto();
        dto.setId(shop.getId());
        dto.setShopName(shop.getShopName());
        dto.setLocations(shop.getLocations());
        dto.setDescription(shop.getDescription());
        if (shop.getUser() != null) {
            dto.setUser_id(shop.getUser().getId());
        } else {
            dto.setUser_id(null);
        }
        return dto;
    }
}
