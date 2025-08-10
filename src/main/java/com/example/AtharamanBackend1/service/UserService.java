package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.ShopOwnerDto;
import com.example.AtharamanBackend1.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);


    UserDto updateUserById(UserDto userDto, Long id);
}
