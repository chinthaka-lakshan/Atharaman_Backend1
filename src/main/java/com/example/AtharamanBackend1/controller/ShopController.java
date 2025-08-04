package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.HotelDto;
import com.example.AtharamanBackend1.dto.ShopOwnerDto;
import com.example.AtharamanBackend1.service.ShopService;
import com.example.AtharamanBackend1.dto.ShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping(consumes = "multipart/form-data")
      public ShopDto addShop(
            @RequestPart("shop") ShopDto shopDto,
            @RequestPart("images") MultipartFile[] images) throws IOException {
            return shopService.createShop(shopDto,images);
    }


    @GetMapping
    public List<ShopDto> getAllShops(){ return shopService.getAllShops();}

    @GetMapping("/{id}")
    public ShopDto getShop(@PathVariable Long id){ return shopService.getShopById(id);}

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ShopDto updateShop(
            @PathVariable Long id,
            @RequestPart("shop") ShopDto shopDto,
            @RequestPart("images") MultipartFile[] images) throws IOException {
        return shopService.updateShopById(id, shopDto, images);
    }

    @PostMapping("/filter")
    public List<ShopDto> getHotelByLocation(@RequestBody ShopDto shopDto){
        return shopService.geyShopsByLocation(shopDto.getLocations());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Long id) {
        shopService.deleteShopById(id);
        return ResponseEntity.ok("Success");
    }
} 
