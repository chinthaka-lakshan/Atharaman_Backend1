package com.example.AtharamanBackend1.controller;

import com.example.AtharamanBackend1.dto.HotelOwnerDto;
import com.example.AtharamanBackend1.dto.UserDto;
import com.example.AtharamanBackend1.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDto userDto(@PathVariable Long id, @RequestBody UserDto userDto){
        return userService.updateUserById(userDto, id);
    }
}
