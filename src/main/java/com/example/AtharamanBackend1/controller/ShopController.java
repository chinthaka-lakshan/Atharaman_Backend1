package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.ShopOwnerDto;
import com.example.AtharamanBackend1.service.ShopService;
import com.example.AtharamanBackend1.dto.ShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping
    public ShopDto addShop(@RequestBody ShopDto shopDto) { return shopService.createShop(shopDto);}

    @GetMapping
    public List<ShopDto> getAllShops(){ return shopService.getAllShops();}

    @GetMapping("/{id}")
    public ShopDto getShop(@PathVariable Long id){ return shopService.getShopById(id);}

    @PutMapping("/{id}")
    public ShopDto updateShop(@RequestBody ShopDto shopDto, @PathVariable Long id){
        return shopService.updateShopById(id, shopDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Long id) {
        shopService.deleteShopById(id);
        return ResponseEntity.ok("Success");
    }
}
