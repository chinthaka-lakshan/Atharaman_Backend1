package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.dto.HotelOwnerDto;
import com.example.AtharamanBackend1.dto.ShopOwnerDto;
import com.example.AtharamanBackend1.dto.VehicleOwnerDto;
import com.example.AtharamanBackend1.service.GuideService;
import com.example.AtharamanBackend1.service.HotelOwnerService;
import com.example.AtharamanBackend1.service.ShopOwnerService;
import com.example.AtharamanBackend1.service.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("api/admin/service_provider_register")
public class AdminServiceProviderRegistration {

    @Autowired
    VehicleOwnerService vehicleOwnerService;

    @Autowired
    ShopOwnerService shopOwnerService;

    @Autowired
    HotelOwnerService hotelOwnerService;

    @Autowired
    GuideService guideService;

    @PostMapping("vehicleowner")
    public VehicleOwnerDto createVehicleOwner(@RequestBody VehicleOwnerDto vehicleOwnerDto){
        return vehicleOwnerService.createVehicleOwner(vehicleOwnerDto);
    }


    @PostMapping("shopowner")
    public ShopOwnerDto addShopOwner(@RequestBody ShopOwnerDto shopOwnerDto) {
        return shopOwnerService.createShopOwner(shopOwnerDto);
    }

    @PostMapping("hotelowner")
    public HotelOwnerDto createHotelOwner(@RequestBody HotelOwnerDto hotelOwnerDto){
        return hotelOwnerService.createHotelOwner(hotelOwnerDto);
    }

    @PostMapping(consumes = "multipart/form-data")
    public GuideDto createGuide(
            @RequestPart("guide") GuideDto guideDto,
            @RequestPart("images") MultipartFile[] images) throws IOException {
        return guideService.createGuide(guideDto, images);
    }

}
