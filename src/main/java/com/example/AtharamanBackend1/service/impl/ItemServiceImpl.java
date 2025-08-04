package com.example.AtharamanBackend1.service.impl;


import com.example.AtharamanBackend1.dto.ItemDto;
import com.example.AtharamanBackend1.entity.Item;
import com.example.AtharamanBackend1.entity.Shop;
import com.example.AtharamanBackend1.repository.ItemRepository;
import com.example.AtharamanBackend1.repository.ShopRepository;
import com.example.AtharamanBackend1.service.ItemService;
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
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public ItemDto createItem(ItemDto  itemDto, MultipartFile[] images)throws IOException {
        Item item = new Item();
        item.setItemName(itemDto.getItemName());
        item.setDiscription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setLocations(itemDto.getLocations());

        Shop shop = shopRepository.findById(itemDto.getShop_id()).orElseThrow(() -> new RuntimeException("Shop not found"));
        item.setShop(shop);

        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            String uploadDir = "uploads/item-packages/";
            Files.createDirectories(Paths.get(uploadDir));

            for (MultipartFile file : images) {
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(uploadDir + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/" + uploadDir + filename);
            }
        }
        item.setImagePaths(imagePaths);

        Item savedItem = itemRepository.save(item);
        itemDto.setId(savedItem.getId());
        return itemDto;
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found"));
        return convertToDto(item);
    }

    @Override
    public ItemDto updateItemById(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found"));
        item.setItemName(itemDto.getItemName());
        item.setDiscription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setLocations(itemDto.getLocations());

        itemRepository.save(item);
        return convertToDto(item);
    }

    @Override
    public void deleteItemById(Long id) {
        Item item= itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found"));
        if (item.getShop() != null) {
            Shop shop = item.getShop();
            shop.setItem(null);
            item.setShop(null);
        }
        itemRepository.delete(item);

    }

    private ItemDto convertToDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setDescription(item.getDiscription());
        dto.setPrice(item.getPrice());
        dto.setLocations(item.getLocations());
        if (item.getShop() != null) {
            dto.setShop_id(item.getShop().getId());
        } else {
            dto.setShop_id(null);
        }
        return dto;
    }
}
