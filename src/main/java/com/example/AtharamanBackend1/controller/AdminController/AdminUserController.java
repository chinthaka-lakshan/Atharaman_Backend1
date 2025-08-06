package com.example.AtharamanBackend1.controller.AdminController;


import com.example.AtharamanBackend1.dto.GuideDto;
import com.example.AtharamanBackend1.dto.UserDto;
import com.example.AtharamanBackend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class AdminUserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();}



    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }



}
