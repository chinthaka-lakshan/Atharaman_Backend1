package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.UserDto;
import com.example.AtharamanBackend1.entity.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;


public interface AuthenticationService {

    AuthenticationResponse register(UserDto request);
    AuthenticationResponse authenticate(UserDto request);
    ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response);
}