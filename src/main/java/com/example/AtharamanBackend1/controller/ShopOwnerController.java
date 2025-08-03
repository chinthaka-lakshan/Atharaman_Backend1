package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.service.ShopOwnerService;
import com.example.AtharamanBackend1.dto.ShopOwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/shop_owners")
public class ShopOwnerController {
    @Autowired
    private ShopOwnerService shopOwnerService;

    @PostMapping
    public ShopOwnerDto addShopOwner(@RequestBody ShopOwnerDto shopOwnerDto) { return shopOwnerService.createShopOwner(shopOwnerDto);}

    @GetMapping
    public List<ShopOwnerDto> getAllShopOwners(){ return shopOwnerService.getAllShopOwners();}

    @GetMapping("/{id}")
    public ShopOwnerDto getShopOwner(@PathVariable Long id){ return shopOwnerService.getShopOwnerById(id);}

    @PutMapping("/{id}")
    public ShopOwnerDto updateShopOwner(@RequestBody ShopOwnerDto shopOwnerDto, @PathVariable Long id){
        return shopOwnerService.updateShopOwnerById(id, shopOwnerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShopOwner(@PathVariable Long id) {
        shopOwnerService.deleteShopOwnerById(id);
        return ResponseEntity.ok("Success");
    }
}
