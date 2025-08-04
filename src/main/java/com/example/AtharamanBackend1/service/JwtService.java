package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUsername(String token);

    boolean isValid(String token, UserDetails user);

    boolean isValidRefreshToken(String token, User user);

    String generateAccessToken(User user);

    String generateRefreshToken(User user);
}
