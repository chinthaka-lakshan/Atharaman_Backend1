package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.ItemDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto,MultipartFile[] images) throws IOException;
    List<ItemDto> getAllItems();
    ItemDto getItemById(Long id);
    ItemDto updateItemById(Long id, ItemDto itemDto,MultipartFile[] images) throws IOException;
    void deleteItemById(Long id);

}
