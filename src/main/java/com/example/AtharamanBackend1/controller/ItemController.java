package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.ItemDto;
import com.example.AtharamanBackend1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = "multipart/form-data")
//    public ItemDto addItem(@RequestBody ItemDto itemDto) {return itemService.createItem(itemDto);}
      public ItemDto createItem(
              @RequestPart("item")  ItemDto itemDto,
              @RequestPart("images") MultipartFile[] images) throws IOException {
              return itemService.createItem(itemDto, images);
    }


    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public ItemDto updateItem(@RequestBody ItemDto itemDto, @PathVariable Long id){
        return itemService.updateItemById(id, itemDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        itemService.deleteItemById(id);
        return ResponseEntity.ok("Success");
    }

}
